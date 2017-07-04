package com.mcfly911.mcraw.thread;

import com.mcfly911.mcraw.jpa.BaseTable;
import com.mcfly911.mcraw.jpa.IService;
import com.mcfly911.mcraw.proxy.AbstractProxy;

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
