package com.mcflykid.crawler.datasource;

import java.util.List;

import com.mcflykid.crawler.jpa.BaseTable;
import com.mcflykid.crawler.jpa.IService;

/**
 * Return all record in table1 which have log1 equal 'NEW'
 * 
 * @author mcflykid
 *
 */
public class DefaultCrawlerDatasource implements CrawlerDatasource {

	@Override
	public List<? extends BaseTable> collect(IService service) {
		return service.getRepo1().findAllByLog1("NEW");
	}

}
