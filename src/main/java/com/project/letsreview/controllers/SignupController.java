package com.project.letsreview.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.project.letsreview.api.request.PostSignupRequest;
import com.project.letsreview.api.response.GenericFailResponse;
import com.project.letsreview.api.response.GenericResponse;
import com.project.letsreview.datamodel.entity.Authentication;
import com.project.letsreview.datamodel.entity.User;
import com.project.letsreview.datamodel.repository.AuthenticationDAOService;
import com.project.letsreview.datamodel.repository.UserDAOService;

@Controller
@ResponseBody
@RequestMapping(value = "signup")
public class SignupController {

	@Autowired
	private UserDAOService userDAO;

	@Autowired
	private AuthenticationDAOService authenticationDAO;

	Gson gson = new Gson();

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> createUser(HttpServletRequest httpRequest,
			@RequestBody @Valid PostSignupRequest postSignupRequest) {

		if (!isUsernameAvailable(postSignupRequest.getUsername())) {
			return generateUsernameUnavailableResponse();
		}
		if (isPhoneNoDuplicate(postSignupRequest.getPhone_no())) {
			return generatePhoneNoDuplicateResponse();
		}
		if (isEmailIdDuplicate(postSignupRequest.getEmail_id())) {
			return generateEmailIdDuplicateResponse();
		}
		return createUser(postSignupRequest);
	}

	private ResponseEntity<String> createUser(PostSignupRequest postSignupRequest) {
		Authentication authentication = new Authentication();

		try{
			User user = new User();
			user.setName(postSignupRequest.getName());
			user.setPhoneNo(postSignupRequest.getPhone_no());
			user.setEmailId(postSignupRequest.getEmail_id());
			user.setUsername(postSignupRequest.getUsername());
			
			userDAO.save(user);

			String hashedPassword = new String(Hex.encodeHex(DigestUtils.sha256(postSignupRequest.getPassword())));
	
			authentication.setUsername(postSignupRequest.getUsername());
			authentication.setPassword(hashedPassword);
			authenticationDAO.save(authentication);
			
			GenericResponse successResponse = new GenericResponse();
			successResponse.setCode("0000");
			successResponse.setStatus("SUCCESS");
			successResponse.setMessage("User has been created successfully");
			return new ResponseEntity<String>(gson.toJson(successResponse), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(gson.toJson(new GenericFailResponse()), HttpStatus.OK);
		}

		

	}

	private boolean isUsernameAvailable(String username) {
		User user = userDAO.findUserByUsername(username);
		return (user == null) ? true : false;
	}

	private ResponseEntity<String> generateUsernameUnavailableResponse() {
		GenericResponse response = new GenericResponse();
		response.setCode("2000");
		response.setStatus("FAIL");
		response.setMessage("username not available");
		return new ResponseEntity<String>(gson.toJson(response), HttpStatus.OK);
	}

	private boolean isPhoneNoDuplicate(String phoneNo) {
		User user = userDAO.findUserByPhoneNo(phoneNo);
		return (user == null) ? false : true;
	}

	private ResponseEntity<String> generatePhoneNoDuplicateResponse() {
		GenericResponse response = new GenericResponse();
		response.setCode("2001");
		response.setStatus("FAIL");
		response.setMessage("This mobile no is already registered");

		return new ResponseEntity<String>(gson.toJson(response), HttpStatus.OK);
	}

	private boolean isEmailIdDuplicate(String emailId){
		User user = userDAO.findUserByEmailId(emailId);
		return (user == null) ? false : true;
	}

	private ResponseEntity<String> generateEmailIdDuplicateResponse() {
		GenericResponse response = new GenericResponse();
		response.setCode("2002");
		response.setStatus("FAIL");
		response.setMessage("This email id is already registered");

		return new ResponseEntity<String>(gson.toJson(response), HttpStatus.OK);
	}
}
