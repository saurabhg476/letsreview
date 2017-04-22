package com.project.letsreview.controllers;

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
import com.project.letsreview.api.request.PostLoginRequest;
import com.project.letsreview.api.response.PostLoginResponse;
import com.project.letsreview.datamodel.entity.Authentication;
import com.project.letsreview.datamodel.entity.User;
import com.project.letsreview.datamodel.entity.UserSession;
import com.project.letsreview.datamodel.repository.AuthenticationDAOService;
import com.project.letsreview.datamodel.repository.UserDAOService;
import com.project.letsreview.datamodel.repository.UserSessionDAOService;
import com.project.letsreview.exceptions.CustomGenericException;
import com.project.letsreview.utils.SessionTokenGenerator;

@Controller
@ResponseBody
@RequestMapping(value = "login")
public class LoginController {

	@Autowired
	private AuthenticationDAOService authenticationDAO;

	@Autowired
	private SessionTokenGenerator sessionTokenGenerator;

	@Autowired
	private UserSessionDAOService userSessionDAO;

	@Autowired
	private UserDAOService userDAO;

	Gson gson = new Gson();

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody @Valid PostLoginRequest postLoginRequest)
			throws CustomGenericException {
		String username = postLoginRequest.getUsername();
		String password = postLoginRequest.getPassword();

		checkIfCredentialsAreValid(username, password);

		String sessionToken;
		if (isAlreadyLoggedIn(username))
			sessionToken = userSessionDAO.findOneByUsername(username).getSessionToken();
		else
			sessionToken = createNewSessionToken(username);

		PostLoginResponse response = createResponse(username, sessionToken);
		return new ResponseEntity<String>(gson.toJson(response), HttpStatus.OK);

	}

	private void checkIfCredentialsAreValid(String username, String password) throws CustomGenericException {
		if (!isUserExists(username))
			throw new CustomGenericException("2004");
		if (!isUsernamePasswordMatches(username, password))
			throw new CustomGenericException("2003");
	}

	private PostLoginResponse createResponse(String username, String sessionToken) {
		PostLoginResponse response = new PostLoginResponse();

		response.setCode("00");
		response.setMessage("User has been logged in successfully");
		response.setStatus("SUCCESS");
		response.setUsername(username);
		response.setSessionToken(sessionToken);
		return response;
	}

	private String createNewSessionToken(String username) {
		String sessionToken;
		sessionToken = sessionTokenGenerator.nextSessionToken();
		UserSession session = new UserSession();
		session.setSessionToken(sessionToken);
		session.setUsername(username);

		userSessionDAO.save(session);
		return sessionToken;
	}

	private boolean isUserExists(String username) {
		User user = userDAO.findUserByUsername(username);
		return user == null ? false : true;
	}

	private boolean isUsernamePasswordMatches(String username, String password) {
		Authentication authentication = authenticationDAO.findOneByUsername(username);
		String hashedPassword = new String(Hex.encodeHex(DigestUtils.sha256(password)));
		String dbRetrivedHashedPassword = authentication.getPassword();
		return hashedPassword.equals(dbRetrivedHashedPassword) ? true : false;
	}

	private boolean isAlreadyLoggedIn(String username) {
		UserSession userSession = userSessionDAO.findOneByUsername(username);
		return userSession != null;
	}
}
