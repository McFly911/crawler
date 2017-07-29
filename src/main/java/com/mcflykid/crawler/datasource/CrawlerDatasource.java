package com.mcflykid.crawler.datasource;

import java.util.List;

import com.mcflykid.crawler.jpa.BaseTable;
import com.mcflykid.crawler.jpa.IService;

/**
 * Provide data for each loop.
 * 
 * @author mcflykid
 *
 */
public interface CrawlerDatasource {

	/**
	 * Easy data source
	 */
	public static final CrawlerDatasource DEFAULT = new DefaultCrawlerDatasource();

	/**
	 * {@link #CrawlerDatasource}
	 * 
	 * @param service
	 * @return list
	 */
	List<? extends BaseTable> collect(IService service);
}
