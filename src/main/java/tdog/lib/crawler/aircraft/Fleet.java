package tdog.lib.crawler.aircraft;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tdog.lib.ThreadLib;
import tdog.lib.crawler.TrumwebCrawlerConfig;
import tdog.lib.crawler.aircraft.stealthskin.Cloak;
import tdog.lib.crawler.aircraft.stealthskin.NullCloak;
import tdog.lib.crawler.service.IService;
import tdog.lib.crawler.table.BaseTable;

public class Fleet {

	public static final String FLAG = "stop-it";
	private static final Logger LOG = LogManager.getLogger(Fleet.class);
	private Constructor<? extends Aircraft> aircraftLoader;
	private List<? extends BaseTable> ssdList;
	private IService service = TrumwebCrawlerConfig.getDefaultService();

	private List<Cloak> cloakList;
	private int threadPerCloak;
	private int cloakSleep;
	private int timeout;
	private Class<? extends Aircraft> aircraftClass;
	private List<String> userAgentList;
	private List<String> referrerList;
	private boolean shuffleSsdList;
	private TableCollector tableCollector;

	public Fleet(int carrierCapacity, int carrierCooldown, int aircraftTimeout, List<Cloak> skinList,
			Class<? extends Aircraft> aircraftClass, List<String> userAgentList, List<String> referrerList,
			boolean shuffleSsdList, TableCollector tableCollector) {
		this.threadPerCloak = carrierCapacity;
		this.cloakSleep = carrierCooldown;
		this.timeout = aircraftTimeout;
		this.cloakList = skinList;
		this.aircraftClass = aircraftClass;
		this.userAgentList = userAgentList;
		this.referrerList = referrerList;
		this.shuffleSsdList = shuffleSsdList;
		this.tableCollector = tableCollector;


	}


	public void start() {
		
		if (cloakList == null || cloakList.isEmpty()){
			cloakList = Arrays.asList(new NullCloak());
		}
		Validate.noNullElements(new Object[] { aircraftClass, service, tableCollector, cloakList });
		
		try {
			aircraftLoader = aircraftClass.getConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			LOG.fatal("", e);
		}

		while (true){
			ssdList = tableCollector.collect(service);
			if(ssdList.size() == 0){
				break;
			}
			if (this.shuffleSsdList) {
				Collections.shuffle(ssdList);
			}
			LOG.info("Collected " + ssdList.size() + " SSDs");
			for (Cloak skin : this.cloakList) {
				new AircraftCarrier(this, skin).start();
			}
			ThreadLib.waitThreadPrefixEnd(AircraftCarrier.class.getName());
		}
		LOG.info("DONE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	public synchronized BaseTable unloadSsd() throws EndOfSsdException {
		if (!ssdList.isEmpty()) {
			BaseTable ssd = ssdList.get(0);
			ssdList.remove(0);
			return ssd;
		}
		throw new EndOfSsdException();
	}

	public int getThreadPerCloak() {
		return threadPerCloak;
	}

	public void setThreadPerCloak(int carrierCapacity) {
		this.threadPerCloak = carrierCapacity;
	}

	public int getCloakSleep() {
		return cloakSleep;
	}

	public void setCloakSleep(int carrierCooldown) {
		this.cloakSleep = carrierCooldown;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int aircraftTimeout) {
		this.timeout = aircraftTimeout;
	}

	public List<Cloak> getCloakList() {
		return cloakList;
	}

	public void setCloakList(List<Cloak> skinList) {
		this.cloakList = skinList;
	}

	public List<? extends BaseTable> getSsdList() {
		return ssdList;
	}

	public void setSsdList(List<? extends BaseTable> ssdList) {
		this.ssdList = ssdList;
	}

	public Class<? extends Aircraft> getAircraftClass() {
		return aircraftClass;
	}

	public void setAircraftClass(Class<? extends Aircraft> aircraftClass) {
		this.aircraftClass = aircraftClass;
	}

	public Constructor<? extends Aircraft> getAircraftLoader() {
		return aircraftLoader;
	}

	public void setAircraftLoader(Constructor<? extends Aircraft> aircraftLoader) {
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
		return shuffleSsdList;
	}

	public void setShuffleSsdList(boolean shuffleSsdList) {
		this.shuffleSsdList = shuffleSsdList;
	}

	public TableCollector getTableCollector() {
		return tableCollector;
	}

	public void setTableCollector(TableCollector tableCollector) {
		this.tableCollector = tableCollector;
	}

}
