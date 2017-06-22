package com.mcfly911.mcraw.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;

public final class HttpLib {

	public static String getDefaultUserAgent() {
		return "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) coc_coc_browser/56.3.120 Chrome/50.3.2661.120 Safari/537.36";
	}

	public static String getDefaultReferrer() {
		return "https://www.google.com";
	}

	public static void main(String[] args) throws Exception {
		// post2("http://lazylearn.com", "COOKIE.txt");

		String s = Jsoup.connect("http://lazylearn.com").cookies(readCookieFromFile("d:/COOKIE.txt")).get().text();
		System.out.println(s);
	}

	/**
	 * Example input: lazylearn.com FALSE / FALSE 0 PHPSESSID
	 * 5br0u13v82q5vp1si6mb6785l2
	 * 
	 * @param path
	 * @return
	 * @author tuandien
	 */
	public static Map<String, String> readCookieFromFile(String path) {
		List<String> lines = TextFileLib.readLineByLine(path);
		Map<String, String> out = new HashMap<String, String>();
		for (String line : lines) {
			String[] parts = line.split("\\t");
			out.put(parts[5], parts[6]);
		}
		return out;
	}

	// HTTP POST request
	public static String post2(String url, String cookieFilePath) throws Exception {

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
		urlParameters.add(new BasicNameValuePair("cn", ""));
		urlParameters.add(new BasicNameValuePair("locale", ""));
		urlParameters.add(new BasicNameValuePair("caller", ""));
		urlParameters.add(new BasicNameValuePair("num", "12345"));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		Header[] as = response.getHeaders("Set-Cookie");
		for (Header a : as)
			System.out.println(a);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		String myString = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		// System.out.println(myString);
		return myString;
	}

	// HTTP POST request
	public static String post(String urlz) throws Exception {

		String url = "https://news.zing.vn";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
		urlParameters.add(new BasicNameValuePair("cn", ""));
		urlParameters.add(new BasicNameValuePair("locale", ""));
		urlParameters.add(new BasicNameValuePair("caller", ""));
		urlParameters.add(new BasicNameValuePair("num", "12345"));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		String myString = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		System.out.println(myString);
		return myString;
	}
}
