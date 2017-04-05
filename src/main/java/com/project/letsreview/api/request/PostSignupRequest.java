package com.project.letsreview.api.request;

import org.hibernate.validator.constraints.NotBlank;

public class PostSignupRequest {

	@NotBlank
	private String name;

	private String phone_no;

	@NotBlank
	private String email_id;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
