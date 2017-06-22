package com.mcfly911.mcraw.thread;

import java.lang.Thread.State;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mcfly911.mcraw.Scraper;
import com.mcfly911.mcraw.exception.EndOfSsdException;
import com.mcfly911.mcraw.jpa.BaseTable;
import com.mcfly911.mcraw.lib.ThreadLib;
import com.mcfly911.mcraw.proxy.AbstractProxy;

/**
 * Launch ScraperThread object with the same PROXY
 * 
 * @author mcfly911
 *
 */
public class ScraperBranch implements Runnable {

	private Thread launchAnAircraft() {
		BaseTable ssd;
		try {
			ssd = scraper.unloadSsd();
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
		aircraft.setSsd(ssd);
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

	public ScraperBranch(Scraper fleet, AbstractProxy skin) {
		this.scraper = fleet;
		this.proxy = skin;
	}

	public void start() {
		new Thread(this).start();
	}

	protected static final Logger LOG = LogManager.getLogger(ScraperBranch.class);

	private Scraper scraper;
	private AbstractProxy proxy;

	public Scraper getScraper() {
		return scraper;
	}

	public AbstractProxy getProxy() {
		return proxy;
	}
}
