package com.mcflykid.crawler;

import java.util.List;

import com.mcflykid.crawler.jpa.BaseTable;
import com.mcflykid.crawler.jpa.IService;

public interface CrawlerInputCollector {
	List<? extends BaseTable> collect(IService service);
}
