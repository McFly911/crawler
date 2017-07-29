package com.mcflykid.crawler.proxy;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mcflykid.crawler.exception.WebException404;
import com.mcflykid.crawler.exception.WebException500;
import com.mcflykid.crawler.exception.WebExceptionCircularRedirec;
import com.mcflykid.crawler.exception.WebExceptionTimeout;
import com.mcflykid.crawler.exception.WebExceptionUnknown;
import com.mcflykid.crawler.lib.HttpLib;

public class JsoupProxy extends AbstractProxy {

	public static void main(String[] args) throws WebException404, WebException500, WebExceptionTimeout,
			WebExceptionCircularRedirec, WebExceptionUnknown {
		JsoupProxy proxy = new JsoupProxy();
		Map<String, String> cookies = HttpLib.readCookieFromFile("d:/cookie.txt");
		ResponseObject ret = proxy.getDocument("http://lazylearn.com", 0, "", "", cookies, null);
		Document document = ret.getDocument();
		HttpLib.showCookie(ret.getCookies());
		String text = document.text();
		System.out.println(text);
	}

	@Override
	public ResponseObject postDocument(String url, int timeout, String userAgent, String referrer,
			Map<String, String> cookies, Map<String, String> headers, Map<String, String> params)
			throws WebException404, WebException500, WebExceptionTimeout, WebExceptionCircularRedirec,
			WebExceptionUnknown {
		if (cookies == null) {
			cookies = new HashMap<String, String>();
		}
		try {
			Response response = Jsoup.connect(url).userAgent(userAgent).referrer(referrer).timeout(timeout).ignoreContentType(true)
					.cookies(cookies).method(Method.POST).data(params).execute();
			// TODO : cc
			Map<String, String> responseCookies = response.cookies();
			Document document = response.parse();
			return new ResponseObject(document, responseCookies);
		} catch (HttpStatusException e) {

			switch (e.getStatusCode()) {
			case 404:
				throw new WebException404();
			case 500:
				throw new WebException500();
			default:
				throw new WebExceptionUnknown("look at CAUSE", e);
			}
		} catch (SocketTimeoutException e) {
			throw new WebExceptionTimeout();
		} catch (Exception e) {
			throw new WebExceptionUnknown("look at CAUSE", e);
		}
	}

	@Override
	public ResponseObject getDocument(String url, int timeout, String userAgent, String referrer,
			Map<String, String> cookies, Map<String, String> headers) throws WebException404, WebException500,
			WebExceptionTimeout, WebExceptionCircularRedirec, WebExceptionUnknown {
		if (cookies == null) {
			cookies = new HashMap<String, String>();
		}
		try {
			Response response = Jsoup.connect(url).userAgent(userAgent).referrer(referrer).timeout(timeout).ignoreContentType(true)
					.cookies(cookies).method(Method.GET).execute();
			// TODO : cc
			Map<String, String> responseCookies = response.cookies();
			Document document = response.parse();
			return new ResponseObject(document, responseCookies);
		} catch (HttpStatusException e) {
			switch (e.getStatusCode()) {
			case 404:
				throw new WebException404();
			case 500:
				throw new WebException500();
			default:
				throw new WebExceptionUnknown("look at CAUSE", e);
			}
		} catch (SocketTimeoutException e) {
			throw new WebExceptionTimeout();
		} catch (Exception e) {
			throw new WebExceptionUnknown("look at CAUSE", e);
		}
	}

	@Override
	public boolean testConnection() {
		// Always true
		return true;
	}

}