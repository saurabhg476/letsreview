package com.project.letsreview.api.response;

public class PostLoginResponse extends GenericResponse {
	private String username;
	private String sessionToken;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
}
