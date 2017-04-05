package com.project.letsreview.api.request;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class PostReviewsRequest {

	@Range(min = 1, max = 5)
	private int rating;

	private String body;

	@NotBlank
	private String username;

	@NotBlank
	private String topic_name;

	@NotBlank
	private String session_token;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTopic_name() {
		return topic_name;
	}

	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}

	public String getSession_token() {
		return session_token;
	}

	public void setSession_token(String session_token) {
		this.session_token = session_token;
	}
}
