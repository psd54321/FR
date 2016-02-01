package com.festival.adf.exceptions;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class FERSGenericException extends Exception {

	// LOGGER to handle custom exceptions
	private static Logger log = Logger.getLogger(FERSGenericException.class);

	public FERSGenericException(String message, Throwable object) {
		super(message, object);
		log.info("Exception Message is :" + message);
	}

}
