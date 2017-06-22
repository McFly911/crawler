package tdog.lib.crawler.aircraft.stealthskin.header;

import java.util.ArrayList;
import java.util.List;

public final class UserAgent {

	public static String getDefault() {
		return "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) coc_coc_browser/56.3.120 Chrome/50.3.2661.120 Safari/537.36";
	}

	public static List<String> getAll() {
		List<String> list = new ArrayList<String>();
		list.add(
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) coc_coc_browser/56.3.120 Chrome/50.3.2661.120 Safari/537.36");
		return list;
	}
}
