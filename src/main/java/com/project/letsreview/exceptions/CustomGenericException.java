package com.project.letsreview.exceptions;

public class CustomGenericException extends Exception {
	private String errorCode;

	public CustomGenericException(String errorCode) {
		super("");
		setErrorCode(errorCode);
	}

	public CustomGenericException(String errorCode, String defaultMessage) {
		super(defaultMessage);
		setErrorCode(errorCode);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
