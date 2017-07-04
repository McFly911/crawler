package com.mcflykid.crawler.exception;

public abstract class AbstractWebException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2975168972698679072L;

	public AbstractWebException() {
	}

	public AbstractWebException(String message, Throwable cause) {
		super(message, cause);
	}

}
