package com.project.letsreview.controllers;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.project.letsreview.api.request.PostLoginRequest;
import com.project.letsreview.datamodel.entity.Authentication;
import com.project.letsreview.datamodel.repository.AuthenticationDAOService;

@Controller
@ResponseBody
@RequestMapping(value = "login")
public class LoginController {

	@Autowired
	private AuthenticationDAOService authenticationDAO;

	Gson gson = new Gson();

	@RequestMapping(method = RequestMethod.POST)
	public void login(@RequestBody String jsonRequest) {
		PostLoginRequest postLoginRequest = gson.fromJson(jsonRequest, PostLoginRequest.class);
		String username = postLoginRequest.getUsername();
		String password = postLoginRequest.getPassword();
		
		//supposing username exists
		if (isPasswordMatches(username, password)) {

		}
	}

	private boolean isPasswordMatches(String username, String password) {
		Authentication authentication = authenticationDAO.findOneByUsername(username);
		String hashedPassword = new String(Hex.encodeHex(DigestUtils.sha256(password)));
		String dbRetrivedHashedPassword = authentication.getPassword();
		return (hashedPassword.equals(dbRetrivedHashedPassword)) ? true : false;
	}

}
