package tdog.lib.crawler.aircraft;

import java.lang.Thread.State;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tdog.lib.ThreadLib;
import tdog.lib.crawler.aircraft.stealthskin.Cloak;
import tdog.lib.crawler.table.BaseTable;

public class AircraftCarrier implements Runnable {


	private Thread launchAnAircraft(){
		BaseTable ssd;
		try {
			ssd = fleet.unloadSsd();
		} catch (EndOfSsdException e) {
			return null;
		}
		Aircraft aircraft = null;
		try {
			aircraft = fleet.getAircraftLoader().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOG.fatal("", e);
			return null;
		}
		aircraft.setSsd(ssd);
		aircraft.setAircraftCarrier(this);
		return aircraft.takeoff();

}
	
	private boolean launchAnAircraftOld(){
			BaseTable ssd;
			try {
				ssd = fleet.unloadSsd();
			} catch (EndOfSsdException e) {
				return false;
			}
			Aircraft aircraft = null;
			try {
				aircraft = fleet.getAircraftLoader().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				LOG.fatal("", e);
				return false;
			}
			aircraft.setSsd(ssd);
			aircraft.setAircraftCarrier(this);
			aircraft.takeoff();
			return true;

	}
	
	
	public void runOld() {
		while (!ThreadLib.flag(Fleet.FLAG)) {
			ThreadLib.waitThreadPrefixRunning(name, fleet.getThreadPerCloak());
			System.out.println("Launch : " + name);
			boolean success = launchAnAircraftOld();
			if (!success){
				break;
			}
			ThreadLib.sleep(fleet.getCloakSleep());
		}
		ThreadLib.waitThreadPrefixEnd(name);
	}
	
	

	
	private List<Thread> threadList;
	@Override
	public void run() {
		threadList = new ArrayList<Thread>();
		int threadLimit = fleet.getThreadPerCloak();
		while (true) { // do
			while (true){ // queue
				int workingThread = 0;
				List<Thread> removes = new ArrayList<Thread>();
				for (Thread t : threadList){
					if (t.getState() != State.TERMINATED){
						workingThread++;
					} else {
						removes.add(t);
					}
				}
				for (Thread t : removes){ // remove
					threadList.remove(t);
				}
				if (workingThread < threadLimit){
					break;
				}
				ThreadLib.sleep(10);
			}
			Thread success = launchAnAircraft();// launch
			if (null == success){
				break;
			} else {
				threadList.add(success);
			}
			ThreadLib.sleep(fleet.getCloakSleep()); // sleep
		}
		while (true){ // finish
			boolean isCompleted = true;
			for (Thread t : threadList){
				if (t.getState() != State.TERMINATED){
					isCompleted = false;
					break;
				}
			}
			if (isCompleted){
				break;
			}
			ThreadLib.sleep(10);
		}
		
	}
	
	public AircraftCarrier(Fleet fleet, Cloak skin) {
		this.fleet = fleet;
		this.skin = skin;
		this.name = skin == null ? FLAGSHIP_NAME : skin.toString();
	}
	
	public void start(){
		new Thread(this, AircraftCarrier.class.getName() + "|" +  System.currentTimeMillis()).start();
	}
	
	protected static final Logger LOG = LogManager.getLogger(AircraftCarrier.class);
	public static final String FLAGSHIP_NAME = "uss-enterprise";
	private Fleet fleet;
	private String name;
	private Cloak skin;
	
	public Fleet getFleet() { return fleet;	}
	public String getName() { return name;	}
	public Cloak getSkin() { return skin; }
}
