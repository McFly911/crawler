package com.mcflykid.crawler.thread;

import com.mcflykid.crawler.jpa.BaseTable;
import com.mcflykid.crawler.jpa.IService;
import com.mcflykid.crawler.proxy.AbstractProxy;

public abstract class ScraperThread implements Runnable {

	protected IService service;
	protected ScraperBranch scraperBranch;
	protected BaseTable data;
	protected AbstractProxy proxy;

	public Thread takeoff() {
		Thread thread = new Thread(this, "ss");
		thread.start();
		return thread;
	}

	public void setAircraftCarrier(ScraperBranch aircraftCarrier) {
		this.scraperBranch = aircraftCarrier;
	}

	public void setData(BaseTable data) {
		this.data = data;
	}

	public void setProxy(AbstractProxy proxy) {
		this.proxy = proxy;
	}

	public void setService(IService service) {
		this.service = service;
	}

}
