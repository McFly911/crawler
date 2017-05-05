package tdog.lib.crawler.aircraft.stealthskin;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;

import tdog.lib.FileLib;
import tdog.lib.TextFileLib;
import tdog.lib.ThreadLib;
import tdog.lib.crawler.aircraft.stealthskin.header.Referrer;
import tdog.lib.crawler.aircraft.stealthskin.header.UserAgent;
import tdog.lib.crawler.aircraft.webexception.WebException404;
import tdog.lib.crawler.aircraft.webexception.WebException500;
import tdog.lib.crawler.aircraft.webexception.WebExceptionCircularRedirec;
import tdog.lib.crawler.aircraft.webexception.WebExceptionTimeout;
import tdog.lib.crawler.aircraft.webexception.WebExceptionUnknown;

public abstract class Cloak {

	private static final Logger LOG = LogManager.getLogger(Cloak.class);
	private static final int CONNECTION_CHECKER_LIMIT = 10;
	private static final int CONNECTION_LAST_CHECK_TIME = 5000;
	private static final String ALIVE_FILE_POSTFIX = ".cloak.txt";

	public static List<Cloak> getTestList(int size) {
		List<Cloak> list = new ArrayList<Cloak>();
		for (int i = 1; i <= size; i++) {
			list.add(new NullCloak());
		}
		return list;
	}

	public static List<Cloak> fromFile(String fileName, Class<? extends Cloak> clazz) {
		return fromFile(fileName, clazz, 0);
	}

	public static List<Cloak> fromFile(Class<? extends Cloak> clazz) {
		return fromFile(clazz.getSimpleName() + ALIVE_FILE_POSTFIX, clazz, 0);
	}

	public static List<Cloak> fromFile(Class<? extends Cloak> clazz, int limit) {
		return fromFile(clazz.getSimpleName() + ALIVE_FILE_POSTFIX, clazz, limit);
	}

	public static List<Cloak> fromFile(String fileName, Class<? extends Cloak> clazz, int limit) {

		List<String> lines = TextFileLib.readLineByLine(fileName);
		List<Cloak> cloaks = new ArrayList<Cloak>();
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
						Cloak cloak = clazz.getConstructor(String.class).newInstance(line);
						if (cloak.testConnection()) {
							if (!fileName.equals(lastCheckedFileName)) {
								synchronized (Cloak.class) {
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

	public Document getDocument(String url) throws Exception, WebException404, WebException500, WebExceptionTimeout,
			WebExceptionCircularRedirec, WebExceptionUnknown {
		return getDocument(url, 0, UserAgent.getDefault(), Referrer.getDefault());
	}

	public String getHtml(String url) throws Exception, WebException404, WebException500, WebExceptionTimeout,
			WebExceptionCircularRedirec, WebExceptionUnknown {
		return getHtml(url, 0, UserAgent.getDefault(), Referrer.getDefault());
	}

	public String getHtml(String url, int timeout, String userAgent, String referrer) throws Exception, WebException404,
			WebException500, WebExceptionTimeout, WebExceptionCircularRedirec, WebExceptionUnknown {
		return getDocument(url, timeout, userAgent, referrer).html();
	}

	public abstract Document getDocument(String url, int timeout, String userAgent, String referrer) throws Exception,
			WebException404, WebException500, WebExceptionTimeout, WebExceptionCircularRedirec, WebExceptionUnknown;

	public abstract boolean testConnection();
}
