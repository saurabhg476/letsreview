package com.project.letsreview.api.response;

public class PostLoginResponse extends GenericResponse {
	private String username;
	private String session_token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionToken() {
		return session_token;
	}

	public void setSessionToken(String sessionToken) {
		this.session_token = sessionToken;
	}
}
