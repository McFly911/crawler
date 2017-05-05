package tdog.lib.crawler.aircraft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;

import tdog.lib.StringLib;
import tdog.lib.crawler.aircraft.stealthskin.Cloak;
import tdog.lib.crawler.aircraft.webexception.WebException404;
import tdog.lib.crawler.aircraft.webexception.WebException500;
import tdog.lib.crawler.aircraft.webexception.WebExceptionCircularRedirec;
import tdog.lib.crawler.aircraft.webexception.WebExceptionTimeout;
import tdog.lib.crawler.aircraft.webexception.WebExceptionUnknown;
import tdog.lib.crawler.service.IService;
import tdog.lib.crawler.table.BaseTable;

public abstract class Aircraft implements Runnable {

	protected static final Logger LOG = LogManager.getLogger(Aircraft.class);

	private AircraftCarrier aircraftCarrier;
	private BaseTable ssd;

	public void takeoffOld() {
		String threadName = getAircraftCarrier().getName();
		new Thread(this, threadName).start();
	}
	
	public Thread takeoff() {
		String threadName = getAircraftCarrier().getName();
		Thread thread =  new Thread(this, threadName);
		thread.start();
		return thread;
	}

	protected Document get(String url) throws Exception, WebException404, WebException500, WebExceptionTimeout,
			WebExceptionCircularRedirec, WebExceptionUnknown {
		if (!url.startsWith("http://") && !url.startsWith("https://")) {
			url = "http://" + url;
		}
		int timeout = getAircraftCarrier().getFleet().getTimeout();
		String userAgent = StringLib.ramdomStringFromList(getAircraftCarrier().getFleet().getUserAgentList());
		String referrer = StringLib.ramdomStringFromList(getAircraftCarrier().getFleet().getReferrerList());
		return getSkin().getDocument(url, timeout, userAgent, referrer);
	}

	public AircraftCarrier getAircraftCarrier() {
		return aircraftCarrier;
	}

	public BaseTable getSsd() {
		return ssd;
	}

	public void setAircraftCarrier(AircraftCarrier aircraftCarrier) {
		this.aircraftCarrier = aircraftCarrier;
	}

	public void setSsd(BaseTable ssd) {
		this.ssd = ssd;
	}

	public Cloak getSkin() {
		return aircraftCarrier.getSkin();
	}

	public IService getService() {
		return getAircraftCarrier().getFleet().getService();
	};
}
