package com.mcflykid.crawler.proxy;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mcflykid.crawler.exception.WebException404;
import com.mcflykid.crawler.exception.WebException500;
import com.mcflykid.crawler.exception.WebExceptionCircularRedirec;
import com.mcflykid.crawler.exception.WebExceptionTimeout;
import com.mcflykid.crawler.exception.WebExceptionUnknown;
import com.mcflykid.crawler.lib.FileLib;
import com.mcflykid.crawler.lib.TextFileLib;
import com.mcflykid.crawler.lib.ThreadLib;

public abstract class AbstractProxy {

	private static final Logger LOG = LogManager.getLogger(AbstractProxy.class);
	private static final int CONNECTION_CHECKER_LIMIT = 10;
	private static final int CONNECTION_LAST_CHECK_TIME = 5000;
	private static final String ALIVE_FILE_POSTFIX = ".cloak.txt";

	public static List<AbstractProxy> getTestList(int size) {
		List<AbstractProxy> list = new ArrayList<AbstractProxy>();
		for (int i = 1; i <= size; i++) {
			list.add(new JsoupProxy());
		}
		return list;
	}

	public static List<AbstractProxy> fromFile(String fileName, Class<? extends AbstractProxy> clazz) {
		return fromFile(fileName, clazz, 0);
	}

	public static List<AbstractProxy> fromFile(Class<? extends AbstractProxy> clazz) {
		return fromFile(clazz.getSimpleName() + ALIVE_FILE_POSTFIX, clazz, 0);
	}

	public static List<AbstractProxy> fromFile(Class<? extends AbstractProxy> clazz, int limit) {
		return fromFile(clazz.getSimpleName() + ALIVE_FILE_POSTFIX, clazz, limit);
	}

	public static List<AbstractProxy> fromFile(String fileName, Class<? extends AbstractProxy> clazz, int limit) {

		List<String> lines = TextFileLib.readLineByLine(fileName);
		List<AbstractProxy> cloaks = new ArrayList<AbstractProxy>();
		String connectionCheckerName = "connection-checker-name-" + System.currentTimeMillis();
		String lastCheckedFileName = clazz.getSimpleName() + ALIVE_FILE_POSTFIX;
		FileLib.createFileIfNotExists(lastCheckedFileName);
		FileLib.createFileIfNotExists(fileName);

		for (String line : lines) {
			if (limit > 0 && cloaks.size() >= limit) {
				break;
			}

			ThreadLib.waitThreadPrefixRunning(connectionCheckerName, CONNECTION_CHECKER_LIMIT);

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						AbstractProxy cloak = clazz.getConstructor(String.class).newInstance(line);
						if (cloak.testConnection()) {
							if (!fileName.equals(lastCheckedFileName)) {
								synchronized (AbstractProxy.class) {
									Files.write(Paths.get(lastCheckedFileName), (line + "\n").getBytes(),
											StandardOpenOption.APPEND);
								}
							}
							synchronized (cloaks) {
								if (limit <= 0 || (limit > 0 && cloaks.size() < limit)) {
									cloaks.add(cloak); // ArrayList is
														// non-synchronized
								}
							}
						}
					} catch (Exception e) {
						LOG.error("Cannot create Cloak with data: " + line, e);
					}
				}
			}, connectionCheckerName).start();
		}
		ThreadLib.waitThreadPrefixEnd(connectionCheckerName);
		LOG.info(cloaks.size() + " SOCKS opened. Waitting for " + CONNECTION_LAST_CHECK_TIME + " milliseconds...");
		ThreadLib.sleep(CONNECTION_LAST_CHECK_TIME);

		return cloaks;
	}

	public abstract ResponseObject getDocument(String url, int timeout, String userAgent, String referrer, Map<String, String> cookies, Map<String, String> headers) throws 
			WebException404, WebException500, WebExceptionTimeout, WebExceptionCircularRedirec, WebExceptionUnknown, Exception;

	public abstract ResponseObject postDocument(String url, int timeout, String userAgent, String referrer, Map<String, String> cookies, Map<String, String> headers, Map<String, String> params) throws 
	WebException404, WebException500, WebExceptionTimeout, WebExceptionCircularRedirec, WebExceptionUnknown, Exception;

	
	public abstract boolean testConnection();
}
