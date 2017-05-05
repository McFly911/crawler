package tdog.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexLib {

	public static String extract(String string, Pattern pattern) {

		Matcher matcher = pattern.matcher(string);
		return matcher.find() ? matcher.group(1) : null;
	}
	
	public static List<String> extractList(String string, Pattern pattern) {
		List<String> l = new ArrayList<String>();
		Matcher matcher = pattern.matcher(string);
		while (matcher.find()){
			l.add(matcher.group());
		}
		return l;
	}

}
