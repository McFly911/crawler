package com.mcflykid.crawler.lib;

import org.springframework.data.domain.PageRequest;

public final class PagingLib {

	public static PageRequest paging(int size) {
		return new PageRequest(0, size);
	}
}
