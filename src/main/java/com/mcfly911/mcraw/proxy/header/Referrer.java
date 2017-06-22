package tdog.lib.crawler.aircraft.stealthskin.header;

import java.util.ArrayList;
import java.util.List;

public final class Referrer {

	public static String getDefault() {
		return "https://www.google.com";
	}

	public static List<String> getAll() {
		List<String> list = new ArrayList<String>();
		list.add("https://www.google.com");
		return list;
	}
}
