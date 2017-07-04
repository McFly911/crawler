package com.mcflykid.crawler.thread;

import java.lang.Thread.State;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mcflykid.crawler.Crawler;
import com.mcflykid.crawler.exception.EndOfSsdException;
import com.mcflykid.crawler.jpa.BaseTable;
import com.mcflykid.crawler.lib.ThreadLib;
import com.mcflykid.crawler.proxy.AbstractProxy;

/**
 * Launch ScraperThread object with the same PROXY
 * 
 * @author mcfly911
 *
 */
public class ScraperBranch implements Runnable {

	private Thread launchAnAircraft() {
		BaseTable data;
		try {
			data = scraper.loadData();
		} catch (EndOfSsdException e) {
			return null;
		}
		ScraperThread aircraft = null;
		try {
			aircraft = scraper.getAircraftLoader().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			LOG.fatal("", e);
			return null;
		}
		aircraft.setData(data);
		aircraft.setAircraftCarrier(this);
		aircraft.setProxy(proxy);
		aircraft.setService(scraper.getService());
		return aircraft.takeoff();

	}

	private List<Thread> threadList;

	@Override
	public void run() {
		threadList = new ArrayList<Thread>();
		int threadLimit = scraper.getThreadPerCloak();
		while (true) { // do
			while (true) { // queue
				int workingThread = 0;
				List<Thread> removes = new ArrayList<Thread>();
				for (Thread t : threadList) {
					if (t.getState() != State.TERMINATED) {
						workingThread++;
					} else {
						removes.add(t);
					}
				}
				for (Thread t : removes) { // remove
					threadList.remove(t);
				}
				if (workingThread < threadLimit) {
					break;
				}
				ThreadLib.sleep(10);
			}
			Thread success = launchAnAircraft();// launch
			if (null == success) {
				break;
			} else {
				threadList.add(success);
			}
			ThreadLib.sleep(scraper.getCloakSleep()); // sleep
		}
		while (true) { // finish
			boolean isCompleted = true;
			for (Thread t : threadList) {
				if (t.getState() != State.TERMINATED) {
					isCompleted = false;
					break;
				}
			}
			if (isCompleted) {
				break;
			}
			ThreadLib.sleep(10);
		}

	}

	public ScraperBranch(Crawler fleet, AbstractProxy skin) {
		this.scraper = fleet;
		this.proxy = skin;
	}

	public void start() {
		new Thread(this, ScraperBranch.class.getName()).start();
	}

	protected static final Logger LOG = LogManager.getLogger(ScraperBranch.class);

	private Crawler scraper;
	private AbstractProxy proxy;

	public Crawler getScraper() {
		return scraper;
	}

	public AbstractProxy getProxy() {
		return proxy;
	}
}
