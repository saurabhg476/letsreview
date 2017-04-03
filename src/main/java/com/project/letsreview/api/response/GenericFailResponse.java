package com.project.letsreview.api.response;

public class GenericFailResponse extends GenericResponse {

	public GenericFailResponse() {
		this.setCode("1000");
		this.setStatus("FAIL");
		this.setMessage("Oops! Something went wrong");
	}
}
