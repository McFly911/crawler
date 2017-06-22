package com.mcfly911.mcraw;

import java.util.ArrayList;
import java.util.List;

import com.mcfly911.mcraw.proxy.AbstractProxy;
import com.mcfly911.mcraw.thread.ScraperThread;

public class ScraperBuilder {

	private int threadPerCloak = 1;
	private int cloakSleep = 0;
	private int timeout = 0;
	private List<AbstractProxy> cloakList = null;
	private List<String> userAgentList = new ArrayList<String>();
	private List<String> referrerList = new ArrayList<String>();
	private boolean shuffleSsdList = true;
	private ScraperInputCollector tableCollector;
	private Class<? extends ScraperThread> aircraftClass;

	public static ScraperBuilder create() {
		return new ScraperBuilder();
	}

	public Scraper build() {
		return new Scraper(threadPerCloak, cloakSleep, timeout, cloakList, aircraftClass,
				userAgentList, referrerList, shuffleSsdList, tableCollector);
	}

	public ScraperBuilder setThreadPerCloak(final int carrierCapacity) {
		this.threadPerCloak = carrierCapacity;
		return this;
	}

	public ScraperBuilder setCloakSleep(final int carrierCooldown) {
		this.cloakSleep = carrierCooldown;
		return this;
	}

	public ScraperBuilder setTimeout(final int aircraftTimeout) {
		this.timeout = aircraftTimeout;
		return this;
	}

	public ScraperBuilder setCloakList(final List<AbstractProxy> skinList) {
		this.cloakList = skinList;
		return this;
	}

	public ScraperBuilder setAircraftClass(final Class<? extends ScraperThread> aircraftClass) {
		this.aircraftClass = aircraftClass;
		return this;
	}

	public ScraperBuilder setUserAgentList(final List<String> userAgentList) {
		this.userAgentList = userAgentList;
		return this;
	}

	public ScraperBuilder setReferrerList(final List<String> referrerList) {
		this.referrerList = referrerList;
		return this;
	}

	public ScraperBuilder setShuffleSsdList(final boolean shuffleSsdList) {
		this.shuffleSsdList = shuffleSsdList;
		return this;
	}

	public ScraperBuilder setTableCollector(final ScraperInputCollector tableCollector) {
		this.tableCollector = tableCollector;
		return this;
	}
	
}
