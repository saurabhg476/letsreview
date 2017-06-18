package com.project.letsreview.api.request;

import org.hibernate.validator.constraints.NotBlank;

public class PostTopicRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String summary;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
