package com.project.letsreview.api.response;

public class GenericSuccessResponse extends GenericResponse {

	public GenericSuccessResponse() {
		setStatus("SUCCESS");
		setCode("00");
		setMessage("");
	}

}
