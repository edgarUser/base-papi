package com.mx.base.papi.exceptions;

public class CurrencyNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6725986190542769457L;

	public CurrencyNotFoundException() {

		super("Currency not supported");
	}
}
