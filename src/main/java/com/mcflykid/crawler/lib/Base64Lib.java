package com.mcflykid.crawler.lib;

import java.io.BufferedInputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public final class Base64Lib {
	public static String imageUrl2Base64String(String imageUrl) throws Exception {
		URL url = new URL(imageUrl);
		BufferedInputStream bis = new BufferedInputStream(url.openConnection().getInputStream());
		byte[] bytes = IOUtils.toByteArray(bis);
		return java.util.Base64.getEncoder().encodeToString(bytes);

	}
}
