package com.mcfly911.mcraw.proxy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CircularRedirectException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.mcfly911.mcraw.exception.WebException404;
import com.mcfly911.mcraw.exception.WebException500;
import com.mcfly911.mcraw.exception.WebExceptionCircularRedirec;
import com.mcfly911.mcraw.exception.WebExceptionTimeout;
import com.mcfly911.mcraw.exception.WebExceptionUnknown;
import com.mcfly911.mcraw.lib.HttpLib;

public class SshProxy extends AbstractProxy {

	private static boolean testFunction() throws WebException404, WebException500, WebExceptionTimeout, WebExceptionCircularRedirec, WebExceptionUnknown, Exception {
		SshProxy ssh = new SshProxy(6969);
		ResponseObject obj = ssh.getDocument("http://lazylearn.com", 0, HttpLib.getDefaultUserAgent(),
				HttpLib.getDefaultReferrer(), HttpLib.readCookieFromFile("d:/cookie.txt"));
		String text = obj.getDocument().text();
		System.out.println(text);

		return true;
	}

	public static void main(String[] args) throws WebException404, WebException500, WebExceptionTimeout, WebExceptionCircularRedirec, WebExceptionUnknown, Exception {
		System.out.println(testFunction());
	}

	private static final Logger LOG = LogManager.getLogger(SshProxy.class);
	private static final int SSH_TIMEOUT = 60000;

	private String ip;
	private int port;
	private String login;
	private String password;

	public String getIp() {
		return ip;
	}

	public int getSocksPort() {
		return socksPort;
	}

	@Override
	public String toString() {
		return "ssh-socks-" + ip;
	}

	private Session session;
	private int socksPort;
	private static AtomicInteger socksPortCounter = new AtomicInteger(6969);

	public SshProxy(String data) {
		String[] arr = data.split("\\|");
		this.ip = arr[0];
		this.login = arr[1];
		this.password = arr[2];
		this.port = 22;
	}

	public SshProxy(String ip, String port, String login, String password) {
		this.ip = ip;
		this.login = login;
		this.password = password;
		this.port = Integer.parseInt(port);
	}

	public SshProxy(int socksPort) {
		this.socksPort = socksPort;
	}

	private static class MyHTTPConnectionSocketFactory extends PlainConnectionSocketFactory {
		@Override
		public Socket createSocket(final HttpContext context) throws IOException {
			InetSocketAddress socksaddr = (InetSocketAddress) context.getAttribute("socks.address");
			Proxy proxy = new Proxy(Proxy.Type.SOCKS, socksaddr);
			return new Socket(proxy);
		}
	}

	private static class MyHTTPSConnectionSocketFactory extends SSLConnectionSocketFactory {
		public MyHTTPSConnectionSocketFactory(final SSLContext sslContext) {
			super(sslContext);
		}

		@Override
		public Socket createSocket(final HttpContext context) throws IOException {
			InetSocketAddress socksaddr = (InetSocketAddress) context.getAttribute("socks.address");
			Proxy proxy = new Proxy(Proxy.Type.SOCKS, socksaddr);
			return new Socket(proxy);
		}
	}

	@Override
	public ResponseObject getDocument(String url, int timeout, String userAgent, String referrer,
			Map<String, String> cookies) throws WebException404, WebException500, WebExceptionTimeout,
			WebExceptionCircularRedirec, WebExceptionUnknown, Exception {

		LOG.info(String.format("Downloading %s with IP: %s, PORT: %d", url, "127.0.0.1", socksPort));

		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", new MyHTTPConnectionSocketFactory())
				.register("https", new MyHTTPSConnectionSocketFactory(SSLContexts.createSystemDefault())).build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);

		InetSocketAddress socksaddr = new InetSocketAddress("127.0.0.1", socksPort);
		HttpClientContext context = HttpClientContext.create();
		context.setAttribute("socks.address", socksaddr);

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
				.setSSLHostnameVerifier(new NoopHostnameVerifier()).setSSLContext(sslContext)
				.setConnectionManager(connectionManager).build();
		HttpGet httpGet = new HttpGet(new URI(url));
		httpGet.setHeader("User-Agent", userAgent);
		httpGet.setHeader("Referer", referrer);

		/*
		 * httpGet.setHeader("Accept",
		 * "application/json, text/javascript; q=0.01");
		 * httpGet.setHeader("Accept-Language",
		 * "sv-SE,sv;q=0.8,en-US;q=0.5,en;q=0.3");
		 * httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		 * httpGet.setHeader("Content-Type",
		 * "application/x-www-form-urlencoded");
		 * httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
		 */

		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet, context);

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
				return new ResponseObject(returnedDocument);
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
		boolean isAlive = false;
		JSch jsch = new JSch();
		try {
			session = jsch.getSession(login, ip, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setConfig("GSSAPIAuthentication", "no");
			LOG.info("Connecting to " + this.toString());
			session.connect(SSH_TIMEOUT);
			isAlive = session.isConnected();
			if (isAlive) {
				String hostKey = session.getHostKey().getKey();
				session.disconnect();
				socksPort = socksPortCounter.getAndIncrement();
				String command = String.format("plink -ssh %s@%s -P %d -T -D %d -pw %s -N -hostkey %s -v -batch", login,
						ip, port, socksPort, password, hostKey);
				Runtime.getRuntime().exec(command);
			}
			return isAlive;
		} catch (Exception e) {
			LOG.error(e.getMessage() + " " + this.toString());
			return false;
		}
	}

}
