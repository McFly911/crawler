package com.mcflykid.crawler;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mcflykid.crawler.datasource.CrawlerDatasource;
import com.mcflykid.crawler.exception.EndOfSsdException;
import com.mcflykid.crawler.jpa.BaseTable;
import com.mcflykid.crawler.jpa.IService;
import com.mcflykid.crawler.lib.ThreadLib;
import com.mcflykid.crawler.proxy.AbstractProxy;
import com.mcflykid.crawler.proxy.JsoupProxy;
import com.mcflykid.crawler.thread.CrawlerGroup;
import com.mcflykid.crawler.thread.CrawlerThread;

public class Crawler {
	
	
	
	/* Get out from Constructor */
	private static final Logger LOG = LogManager.getLogger(Crawler.class);
	private Constructor<? extends CrawlerThread> aircraftLoader;
	private List<? extends BaseTable> dataList;
	private IService service = CrawlerConfig.getService();
	/**/

	/* Constructor */
	private List<AbstractProxy> proxies = null;
	private static int threads = 1;
	private int sleep = 0;
	private int timeout = 0;
	private Class<? extends CrawlerThread> clazz;
	private List<String> userAgentList;
	private List<String> referrerList;
	private boolean randomizeInput;
	private CrawlerDatasource datasource;
	/**/

	/**
	 * Start with given parameters
	 * 
	 * @param clazz
	 * @param datasource
	 */
	public static void start(Class<? extends CrawlerThread> clazz, CrawlerDatasource datasource) {
		Crawler crawler = new Crawler();
		crawler.setClazz(clazz);
		crawler.setDatasource(datasource);
		crawler.internalStart();
	}

	/**
	 * Start with given parameters
	 * 
	 * @param clazz
	 * @param datasource
	 */
	public static void start(Class<? extends CrawlerThread> clazz) {
		Crawler.start(clazz, CrawlerDatasource.DEFAULT);
	}

	private void internalStart() {

		if (proxies == null || proxies.isEmpty()) {
			proxies = Arrays.asList(new JsoupProxy());
		}
		Validate.noNullElements(new Object[] { clazz, datasource });

		try {
			aircraftLoader = clazz.getConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			LOG.fatal("", e);
		}

		while (true) {
			dataList = datasource.collect(service);
			if (dataList.size() == 0) {
				break;
			}
			if (this.randomizeInput) {
				Collections.shuffle(dataList);
			}
			LOG.info("Collected " + dataList.size() + " SSDs");
			for (AbstractProxy skin : this.proxies) {
				new CrawlerGroup(this, skin).start();
			}
			ThreadLib.waitThreadPrefixEnd(CrawlerGroup.class.getName());
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

	/**
	 * Clear all table on database.
	 */
	public static void reset() {
		CrawlerConfig.resetDatabase();
	}

	public int getThreadPerCloak() {
		return threads;
	}

	public void setThreadPerCloak(int carrierCapacity) {
		this.threads = carrierCapacity;
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
		return proxies;
	}

	public void setCloakList(List<AbstractProxy> skinList) {
		this.proxies = skinList;
	}

	public List<? extends BaseTable> getSsdList() {
		return dataList;
	}

	public void setSsdList(List<? extends BaseTable> ssdList) {
		this.dataList = ssdList;
	}

	public Class<? extends CrawlerThread> getAircraftClass() {
		return clazz;
	}

	public void setAircraftClass(Class<? extends CrawlerThread> aircraftClass) {
		this.clazz = aircraftClass;
	}

	public Constructor<? extends CrawlerThread> getAircraftLoader() {
		return aircraftLoader;
	}

	public void setAircraftLoader(Constructor<? extends CrawlerThread> aircraftLoader) {
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

	public CrawlerDatasource getTableCollector() {
		return datasource;
	}

	public void setTableCollector(CrawlerDatasource tableCollector) {
		this.datasource = tableCollector;
	}

	public List<AbstractProxy> getProxies() {
		return proxies;
	}

	public void setProxies(List<AbstractProxy> proxies) {
		this.proxies = proxies;
	}

	public static int getThreads() {
		return threads;
	}

	public static void setThreads(int threads) {
		Crawler.threads = threads;
	}

	public int getSleep() {
		return sleep;
	}

	public void setSleep(int sleep) {
		this.sleep = sleep;
	}

	public Class<? extends CrawlerThread> getClazz() {
		return clazz;
	}

	public void setClazz(Class<? extends CrawlerThread> clazz) {
		this.clazz = clazz;
	}

	public CrawlerDatasource getDatasource() {
		return datasource;
	}

	public void setDatasource(CrawlerDatasource datasource) {
		this.datasource = datasource;
	}

}
