package com.saurabh.letsreview.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.saurabh.letsreview.api.request.PostSignupRequest;
import com.saurabh.letsreview.api.response.GenericResponse;
import com.saurabh.letsreview.datamodel.entity.User;
import com.saurabh.letsreview.datamodel.repository.UserDAOService;

@Controller
@ResponseBody
@RequestMapping(value = "signup")
public class SignupController {

	@Autowired
	private UserDAOService userDAO;

	Gson gson = new Gson();

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> createUser(HttpServletRequest httpRequest, @RequestBody String jsonRequest) {
		PostSignupRequest postSignupRequest = gson.fromJson(jsonRequest, PostSignupRequest.class);
		if (!isUsernameAvailable(postSignupRequest.getUsername())) {
			GenericResponse response = new GenericResponse();
			response.setCode("2000");
			response.setStatus("FAIL");
			response.setMessage("username not available.");
			return new ResponseEntity<String>(gson.toJson(response), HttpStatus.OK);
		}
		return null;
	}

	private boolean isUsernameAvailable(String username) {
		User user = userDAO.findUserByUsername(username);
		return (user == null) ? true : false;
	}
}
