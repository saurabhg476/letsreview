package com.saurabh.letsreview.api.response;

public class GenericFailureResponse extends GenericResponse {

	public GenericFailureResponse() {
		this.setCode("1000");
		this.setStatus("FAIL");
		this.setMessage("Oops! Something went wrong");
	}
}
