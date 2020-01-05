package com.csv.customException;

public class ServiceFailureException extends Exception{
private static final long serialVersionUID = 1L;
	
	private String code;
	private String message;

	public ServiceFailureException(String message) {
		this.message = message;
	}

	public ServiceFailureException(String code, String message) {

		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
