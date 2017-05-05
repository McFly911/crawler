package tdog.lib;

import java.io.File;
import java.util.Set;

public class ThreadLib {

	public static boolean flag(String id) {
		File f = new File("flag/" + id);
		return f.exists();
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void waitThreadPrefixEnd(String threadNamePrefix) {
		waitThreadPrefixRunning(threadNamePrefix, 1);
	}

	public static void waitThreadPrefixRunning(String threadNamePrefix, int maximumThreadNumberInTime) {
		while (countThreadNameStartWith(threadNamePrefix) >= maximumThreadNumberInTime) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static int countThreadNameStartWith(String startName) {
		Set<Thread> threads = getAllThreads();
		int sum = 0;
		for (Thread thread : threads) {
			if (thread.getName().startsWith(startName)) {
				sum++;
			}
		}
		return sum;
	}

	private static Set<Thread> getAllThreads() {
		return Thread.getAllStackTraces().keySet();
	}

}
