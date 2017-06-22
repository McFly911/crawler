package com.mcfly911.mcraw.proxy;

import java.net.SocketTimeoutException;
import java.util.Map;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mcfly911.mcraw.exception.WebException404;
import com.mcfly911.mcraw.exception.WebException500;
import com.mcfly911.mcraw.exception.WebExceptionCircularRedirec;
import com.mcfly911.mcraw.exception.WebExceptionTimeout;
import com.mcfly911.mcraw.exception.WebExceptionUnknown;

public class NullProxy extends AbstractProxy {

	@Override
	public ResponseObject getDocument(String url, int timeout, String userAgent, String referrer,
			Map<String, String> cookies) throws WebException404, WebException500, WebExceptionTimeout,
			WebExceptionCircularRedirec, WebExceptionUnknown {
		try {
			Document doc = Jsoup.connect(url).userAgent(userAgent).referrer(referrer).timeout(timeout).cookies(cookies)
					.get();
			return new ResponseObject(doc);
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