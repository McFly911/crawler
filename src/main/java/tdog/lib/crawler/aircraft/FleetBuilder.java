package tdog.lib.crawler.aircraft;

import java.util.List;

import tdog.lib.crawler.aircraft.stealthskin.Cloak;
import tdog.lib.crawler.aircraft.stealthskin.header.Referrer;
import tdog.lib.crawler.aircraft.stealthskin.header.UserAgent;

public class FleetBuilder {

	private int threadPerCloak = 1;
	private int cloakSleep = 0;
	private int timeout = 0;
	private List<Cloak> cloakList;
	private Class<? extends Aircraft> aircraftClass;
	private List<String> userAgentList = UserAgent.getAll();
	private List<String> referrerList = Referrer.getAll();
	private boolean shuffleSsdList = true;
	private TableCollector tableCollector;

	public static FleetBuilder create() {
		return new FleetBuilder();
	}

	public Fleet build() {
		return new Fleet(threadPerCloak, cloakSleep, timeout, cloakList, aircraftClass,
				userAgentList, referrerList, shuffleSsdList, tableCollector);
	}

	public FleetBuilder setThreadPerCloak(final int carrierCapacity) {
		this.threadPerCloak = carrierCapacity;
		return this;
	}

	public FleetBuilder setCloakSleep(final int carrierCooldown) {
		this.cloakSleep = carrierCooldown;
		return this;
	}

	public FleetBuilder setTimeout(final int aircraftTimeout) {
		this.timeout = aircraftTimeout;
		return this;
	}

	public FleetBuilder setCloakList(final List<Cloak> skinList) {
		this.cloakList = skinList;
		return this;
	}

	public FleetBuilder setAircraftClass(final Class<? extends Aircraft> aircraftClass) {
		this.aircraftClass = aircraftClass;
		return this;
	}

	public FleetBuilder setUserAgentList(final List<String> userAgentList) {
		this.userAgentList = userAgentList;
		return this;
	}

	public FleetBuilder setReferrerList(final List<String> referrerList) {
		this.referrerList = referrerList;
		return this;
	}

	public FleetBuilder setShuffleSsdList(final boolean shuffleSsdList) {
		this.shuffleSsdList = shuffleSsdList;
		return this;
	}

	public FleetBuilder setTableCollector(final TableCollector tableCollector) {
		this.tableCollector = tableCollector;
		return this;
	}
	
}
