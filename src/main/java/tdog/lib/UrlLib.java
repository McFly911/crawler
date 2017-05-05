package tdog.lib;

import java.net.URI;
import java.net.URISyntaxException;

public final class UrlLib {

	public static String addPrefix(String url) {
		if (!url.startsWith("http://") && !url.startsWith("https://")) {
			url = "http://" + url;
		}
		return url;
	}

	public static String getHost(String check) {
		URI uri;
		try {
			uri = new URI(check);
			return uri.getHost();
		} catch (URISyntaxException e) {
			return null;
		}
	}

	public static boolean isSubpage(String root, String check) {
		root = addPrefix(root);
		check = addPrefix(check);
		URI uri1, uri2;
		try {
			uri1 = new URI(check);
			uri2 = new URI(root);
			return uri1.getHost().equalsIgnoreCase(uri2.getHost());
		} catch (Exception e) {
			return false;
		}
	}

}
