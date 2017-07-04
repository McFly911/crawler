package com.mcflykid.crawler;

import java.util.ArrayList;
import java.util.List;

import com.mcflykid.crawler.proxy.AbstractProxy;
import com.mcflykid.crawler.thread.ScraperThread;

public class CrawlerBuilder {

	private int threadPerCloak = 1;
	private int cloakSleep = 0;
	private int timeout = 0;
	private List<AbstractProxy> cloakList = null;
	private List<String> userAgentList = new ArrayList<String>();
	private List<String> referrerList = new ArrayList<String>();
	private boolean shuffleSsdList = true;
	private CrawlerInputCollector tableCollector;
	private Class<? extends ScraperThread> aircraftClass;

	public static CrawlerBuilder create() {
		return new CrawlerBuilder();
	}

	public Crawler build() {
		return new Crawler(threadPerCloak, cloakSleep, timeout, cloakList, aircraftClass,
				userAgentList, referrerList, shuffleSsdList, tableCollector);
	}

	public CrawlerBuilder setThreadPerCloak(final int carrierCapacity) {
		this.threadPerCloak = carrierCapacity;
		return this;
	}

	public CrawlerBuilder setCloakSleep(final int carrierCooldown) {
		this.cloakSleep = carrierCooldown;
		return this;
	}

	public CrawlerBuilder setTimeout(final int aircraftTimeout) {
		this.timeout = aircraftTimeout;
		return this;
	}

	public CrawlerBuilder setCloakList(final List<AbstractProxy> skinList) {
		this.cloakList = skinList;
		return this;
	}

	public CrawlerBuilder setAircraftClass(final Class<? extends ScraperThread> aircraftClass) {
		this.aircraftClass = aircraftClass;
		return this;
	}

	public CrawlerBuilder setUserAgentList(final List<String> userAgentList) {
		this.userAgentList = userAgentList;
		return this;
	}

	public CrawlerBuilder setReferrerList(final List<String> referrerList) {
		this.referrerList = referrerList;
		return this;
	}

	public CrawlerBuilder setShuffleSsdList(final boolean shuffleSsdList) {
		this.shuffleSsdList = shuffleSsdList;
		return this;
	}

	public CrawlerBuilder setTableCollector(final CrawlerInputCollector tableCollector) {
		this.tableCollector = tableCollector;
		return this;
	}
	
}
