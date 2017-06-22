package com.mcfly911.mcraw.lib;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

public final class EmailLib {

	@SuppressWarnings("unused")
	private static final String PATTERN = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";

	private static List<String> topDomains;

	public static boolean isEmailHaveValidTopDomain(String email) {

		if (topDomains == null) {
			synchronized (EmailLib.class) {
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				try {
					String rs = IOUtils.toString(classLoader.getResourceAsStream("top-level-domains.txt"));
					String lines[] = rs.split("\\r\\n");
					topDomains = Arrays.asList(lines);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		email = email.toUpperCase();
		boolean found = false;
		for (String topDomain : topDomains) {
			if (email.endsWith("." + topDomain)) {
				found = true;
				break;
			}
		}

		return found;
	}

}
