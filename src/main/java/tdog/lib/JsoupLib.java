package tdog.lib;

import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public final class JsoupLib {

	public static Document get(String url) {
		return get(url, null, 0, null);
	}

	public static Document get(String url, String userAgent, int timeoutMilisecond, Logger logger) {

		Document document = null;
		Connection con = null;
		while (document == null) {
			try {
				con = Jsoup.connect(url).timeout(timeoutMilisecond);
				if (userAgent != null) {
					con.userAgent(userAgent);
				}
				document = con.get();

			} catch (HttpStatusException e) {
				if (e.getStatusCode() == 404) {
					if (logger != null) {
						logger.error("404: " + url);
					}
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return document;
	}

}
