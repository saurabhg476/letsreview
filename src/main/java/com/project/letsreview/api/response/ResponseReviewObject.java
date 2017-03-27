package com.project.letsreview.api.response;

import java.util.Date;



public class ResponseReviewObject {
	private String body;
	private Date created_on;
	private int rating;
	private ResponseUserObject user = new ResponseUserObject();

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public ResponseUserObject getUser() {
		return user;
	}

	public void setUser(ResponseUserObject user) {
		this.user = user;
	}
}
