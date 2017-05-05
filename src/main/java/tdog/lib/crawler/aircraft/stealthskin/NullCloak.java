package tdog.lib.crawler.aircraft.stealthskin;

import java.net.SocketTimeoutException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import tdog.lib.crawler.aircraft.webexception.WebException;
import tdog.lib.crawler.aircraft.webexception.WebException404;
import tdog.lib.crawler.aircraft.webexception.WebException500;
import tdog.lib.crawler.aircraft.webexception.WebExceptionTimeout;
import tdog.lib.crawler.aircraft.webexception.WebExceptionUnknown;

public class NullCloak extends Cloak {

	@Override
	public Document getDocument(String url, int timeout, String userAgent, String referrer)
			throws Exception, WebException {
		try {
			return Jsoup.connect(url).userAgent(userAgent).referrer(referrer).timeout(timeout).get();
		} catch (HttpStatusException e) {

			switch (e.getStatusCode()) {
			case 404:
				throw new WebException404();
			case 500:
				throw new WebException500();
			default:
				throw new WebExceptionUnknown("look at CAUSE", e);
			}
		} catch (SocketTimeoutException e){ 
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