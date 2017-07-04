package com.mcflykid.crawler.lib;

import java.util.List;
import java.util.Random;

public final class StringLib {

	private static final Random RANDOM_GEN = new Random();

	public static String ramdomStringFromList(List<String> list) {
		int index = RANDOM_GEN.nextInt(list.size());
		return list.get(index);
	}
}
