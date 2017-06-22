package com.mcfly911.mcraw.thread;

import org.jsoup.nodes.Document;

import com.mcfly911.mcraw.exception.WebException404;
import com.mcfly911.mcraw.exception.WebException500;
import com.mcfly911.mcraw.exception.WebExceptionCircularRedirec;
import com.mcfly911.mcraw.exception.WebExceptionTimeout;
import com.mcfly911.mcraw.exception.WebExceptionUnknown;
import com.mcfly911.mcraw.jpa.BaseTable;
import com.mcfly911.mcraw.jpa.IService;
import com.mcfly911.mcraw.lib.StringLib;
import com.mcfly911.mcraw.proxy.AbstractProxy;

public abstract class ScraperThread implements Runnable {

	protected IService service;
	private ScraperBranch aircraftCarrier;
	protected BaseTable ssd;
	protected AbstractProxy proxy;

	public Thread takeoff() {
		Thread thread =  new Thread(this);
		thread.start();
		return thread;
	}

	protected Document get(String url) throws Exception, WebException404, WebException500, WebExceptionTimeout,
			WebExceptionCircularRedirec, WebExceptionUnknown {
		if (!url.startsWith("http://") && !url.startsWith("https://")) {
			url = "http://" + url;
		}
		int timeout = aircraftCarrier.getScraper().getTimeout();
		String userAgent = StringLib.ramdomStringFromList(aircraftCarrier.getScraper().getUserAgentList());
		String referrer = StringLib.ramdomStringFromList(aircraftCarrier.getScraper().getReferrerList());
		//return aircraftCarrier.getSkin().getDocument(url, timeout, userAgent, referrer);
		return null;
	}

	public void setAircraftCarrier(ScraperBranch aircraftCarrier) {
		this.aircraftCarrier = aircraftCarrier;
	}

	public void setSsd(BaseTable ssd) {
		this.ssd = ssd;
	}

	public void setProxy(AbstractProxy proxy) {
		this.proxy = proxy;
	}

	public void setService(IService service) {
		this.service = service;
	}
	
	
	
	
}
