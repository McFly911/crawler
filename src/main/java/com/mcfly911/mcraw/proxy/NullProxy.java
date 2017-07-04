package com.mcfly911.mcraw.proxy;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CircularRedirectException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mcfly911.mcraw.exception.WebException404;
import com.mcfly911.mcraw.exception.WebException500;
import com.mcfly911.mcraw.exception.WebExceptionCircularRedirec;
import com.mcfly911.mcraw.exception.WebExceptionTimeout;
import com.mcfly911.mcraw.exception.WebExceptionUnknown;
import com.mcfly911.mcraw.lib.HttpLib;

public class NullProxy extends AbstractProxy {

	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger(NullProxy.class);



	public ResponseObject suckDocument(String url, int timeout, String userAgent, String referrer,
			Map<String, String> cookies, Map<String, String> headers, Map<String, String> params, boolean isGet)
			throws WebException404, WebException500, WebExceptionTimeout, WebExceptionCircularRedirec,
			WebExceptionUnknown, Exception {

		HttpClientContext context = HttpClientContext.create();

		// Create a trust manager that does not validate certificate chains
		// Install the all-trusting trust manager
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).setAuthenticationEnabled(true).build();

		HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
				.setSSLHostnameVerifier(new NoopHostnameVerifier()).setSSLContext(sslContext).build();

		HttpRequestBase httpGetPost = null;
		if (isGet) {
			httpGetPost = new HttpGet(new URI(url));
		} else {
			httpGetPost = new HttpPost(new URI(url));
			List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				postParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			((HttpPost) httpGetPost).setEntity(new UrlEncodedFormEntity(postParameters));
		}

		httpGetPost.setHeader("User-Agent", userAgent);
		httpGetPost.setHeader("Referer", referrer);
		if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpGetPost.setHeader(entry.getKey(), entry.getValue());
			}
		}
		if (cookies != null) {
			httpGetPost.setHeader("Cookie", HttpLib.cookieToString(cookies));
		}

		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGetPost, context);

		} catch (ClientProtocolException e) {
			if (e.getCause() instanceof CircularRedirectException) {
				throw new WebExceptionCircularRedirec();
			} else {
				throw new WebExceptionUnknown("look at CAUSE", e);
			}
		} catch (SocketTimeoutException e) {
			throw new WebExceptionTimeout();
		}

		int httpCode = response.getStatusLine().getStatusCode();

		if (httpCode == 200) {
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();
			String str = null;
			ByteArrayOutputStream baos = null;
			try {
				baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[9000000];
				int length = 0;
				while ((length = inputStream.read(buffer)) != -1) {
					baos.write(buffer, 0, length);
				}
				str = new String(baos.toByteArray(), "UTF-8");
				Document returnedDocument = Jsoup.parse(str);
				Header[] headersRet = response.getHeaders("Set-Cookie");
				Map<String, String> returnCookies = HttpLib.readCookieFromHeaders(headersRet);
				return new ResponseObject(returnedDocument, returnCookies);
			} finally {
				baos.close();
				inputStream.close();
			}
		}
		switch (httpCode) {
		case 404:
			throw new WebException404();
		case 500:
			throw new WebException500();
		default:
			throw new WebExceptionUnknown(response.getStatusLine().toString(), null);
		}
	}

	@Override
	public boolean testConnection() {
		return true;
	}

	@Override
	public ResponseObject postDocument(String url, int timeout, String userAgent, String referrer,
			Map<String, String> cookies, Map<String, String> headers, Map<String, String> params)
			throws WebException404, WebException500, WebExceptionTimeout, WebExceptionCircularRedirec,
			WebExceptionUnknown, Exception {
		// TODO Auto-generated method stub
		return suckDocument(url, timeout, userAgent, referrer, cookies, headers, params, false);
	}

	@Override
	public ResponseObject getDocument(String url, int timeout, String userAgent, String referrer,
			Map<String, String> cookies, Map<String, String> headers) throws WebException404, WebException500,
			WebExceptionTimeout, WebExceptionCircularRedirec, WebExceptionUnknown, Exception {
		return suckDocument(url, timeout, userAgent, referrer, cookies, headers, null, true);
	}

}
