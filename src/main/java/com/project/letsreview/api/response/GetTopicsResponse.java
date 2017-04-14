package com.project.letsreview.api.response;

import java.util.List;

public class GetTopicsResponse extends GenericResponse {
	List<String> topicNames;

	public List<String> getTopicNames() {
		return topicNames;
	}

	public void setTopicNames(List<String> topicNames) {
		this.topicNames = topicNames;
	}

}
