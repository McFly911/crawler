package com.mcfly911.mcraw;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mcfly911.mcraw.thread.ScraperThread;
import com.mcfly911.mcraw.exception.EndOfSsdException;
import com.mcfly911.mcraw.jpa.BaseTable;
import com.mcfly911.mcraw.jpa.IService;
import com.mcfly911.mcraw.lib.ThreadLib;
import com.mcfly911.mcraw.proxy.AbstractProxy;
import com.mcfly911.mcraw.proxy.JsoupProxy;
import com.mcfly911.mcraw.thread.ScraperBranch;

public class Scraper {

	/* Get out from Constructor */
	private static final Logger LOG = LogManager.getLogger(Scraper.class);
	private Constructor<? extends ScraperThread> aircraftLoader;
	private List<? extends BaseTable> dataList;
	private IService service = ScraperConfig.getService();
	/**/

	/* Constructor */
	private List<AbstractProxy> proxyList;
	private int threadPerProxy;
	private int sleep;
	private int timeout;
	private Class<? extends ScraperThread> aircraftClass;
	private List<String> userAgentList;
	private List<String> referrerList;
	private boolean randomizeInput;
	private ScraperInputCollector inputCollector;
	/**/

	public Scraper(int carrierCapacity, int carrierCooldown, int aircraftTimeout, List<AbstractProxy> skinList,
			Class<? extends ScraperThread> aircraftClass, List<String> userAgentList, List<String> referrerList,
			boolean shuffleSsdList, ScraperInputCollector tableCollector) {
		this.threadPerProxy = carrierCapacity;
		this.sleep = carrierCooldown;
		this.timeout = aircraftTimeout;
		this.proxyList = skinList;
		this.aircraftClass = aircraftClass;
		this.userAgentList = userAgentList;
		this.referrerList = referrerList;
		this.randomizeInput = shuffleSsdList;
		this.inputCollector = tableCollector;

	}

	public void start() {

		if (proxyList == null || proxyList.isEmpty()) {
			proxyList = Arrays.asList(new JsoupProxy());
		}
		Validate.noNullElements(new Object[] { aircraftClass, service, inputCollector, proxyList });

		try {
			aircraftLoader = aircraftClass.getConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			LOG.fatal("", e);
		}

		while (true) {
			dataList = inputCollector.collect(service);
			if (dataList.size() == 0) {
				break;
			}
			if (this.randomizeInput) {
				Collections.shuffle(dataList);
			}
			LOG.info("Collected " + dataList.size() + " SSDs");
			for (AbstractProxy skin : this.proxyList) {
				new ScraperBranch(this, skin).start();
			}
			ThreadLib.waitThreadPrefixEnd(ScraperBranch.class.getName());
		}
		LOG.info("DONE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	public synchronized BaseTable loadData() throws EndOfSsdException {
		if (!dataList.isEmpty()) {
			BaseTable ssd = dataList.get(0);
			dataList.remove(0);
			return ssd;
		}
		throw new EndOfSsdException();
	}

	public int getThreadPerCloak() {
		return threadPerProxy;
	}

	public void setThreadPerCloak(int carrierCapacity) {
		this.threadPerProxy = carrierCapacity;
	}

	public int getCloakSleep() {
		return sleep;
	}

	public void setCloakSleep(int carrierCooldown) {
		this.sleep = carrierCooldown;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int aircraftTimeout) {
		this.timeout = aircraftTimeout;
	}

	public List<AbstractProxy> getCloakList() {
		return proxyList;
	}

	public void setCloakList(List<AbstractProxy> skinList) {
		this.proxyList = skinList;
	}

	public List<? extends BaseTable> getSsdList() {
		return dataList;
	}

	public void setSsdList(List<? extends BaseTable> ssdList) {
		this.dataList = ssdList;
	}

	public Class<? extends ScraperThread> getAircraftClass() {
		return aircraftClass;
	}

	public void setAircraftClass(Class<? extends ScraperThread> aircraftClass) {
		this.aircraftClass = aircraftClass;
	}

	public Constructor<? extends ScraperThread> getAircraftLoader() {
		return aircraftLoader;
	}

	public void setAircraftLoader(Constructor<? extends ScraperThread> aircraftLoader) {
		this.aircraftLoader = aircraftLoader;
	}

	public IService getService() {
		return service;
	}

	public void setService(IService service) {
		this.service = service;
	}

	public List<String> getUserAgentList() {
		return userAgentList;
	}

	public void setUserAgentList(List<String> userAgentList) {
		this.userAgentList = userAgentList;
	}

	public List<String> getReferrerList() {
		return referrerList;
	}

	public void setReferrerList(List<String> referrerList) {
		this.referrerList = referrerList;
	}

	public boolean isShuffleSsdList() {
		return randomizeInput;
	}

	public void setShuffleSsdList(boolean shuffleSsdList) {
		this.randomizeInput = shuffleSsdList;
	}

	public ScraperInputCollector getTableCollector() {
		return inputCollector;
	}

	public void setTableCollector(ScraperInputCollector tableCollector) {
		this.inputCollector = tableCollector;
	}

}
