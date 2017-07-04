package com.mcfly911.mcraw.proxy;

import java.util.Map;

import org.jsoup.nodes.Document;

/**
 * Return Document and Cookies Map
 * 
 * @author mcfly911
 *
 */
public class ResponseObject {

	/**
	 * J-soup Object
	 */
	private Document document;

	/**
	 * Cookies Map
	 */
	private Map<String, String> cookies;

	/**
	 * Headers
	 */
	private Map<String, String> headers;

	/**
	 * 
	 * @param document
	 * @param cookies
	 */
	public ResponseObject(Document document, Map<String, String> cookies) {
		this.document = document;
		this.cookies = cookies;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public ResponseObject(Document document, Map<String, String> cookies, Map<String, String> headers) {
		this.document = document;
		this.cookies = cookies;
		this.headers = headers;
	}

	/**
	 * 
	 * @param document
	 */
	public ResponseObject(Document document) {
		this.document = document;
	}

	/**
	 * 
	 */
	public ResponseObject() {
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

}
