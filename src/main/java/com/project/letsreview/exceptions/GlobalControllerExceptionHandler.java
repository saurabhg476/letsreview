package com.project.letsreview.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.gson.Gson;
import com.project.letsreview.api.response.GenericResponse;
import com.project.letsreview.datamodel.entity.ErrorCode;
import com.project.letsreview.datamodel.repository.ErrorCodeDAOService;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	Gson gson = new Gson();

	@Autowired
	private ErrorCodeDAOService errorCodeDAO;

	@ExceptionHandler(CustomGenericException.class)
	private ResponseEntity<String> exceptionHandler(CustomGenericException e) {
		ErrorCode errorCode = errorCodeDAO.findOneByCode(e.getErrorCode());

		GenericResponse response = new GenericResponse();
		response.setCode(errorCode.getCode());
		response.setMessage(errorCode.getMessage());
		response.setStatus("FAIL");
		return new ResponseEntity<String>(gson.toJson(response), HttpStatus.OK);
	}

}
