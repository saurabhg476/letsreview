package com.project.letsreview.api.response;

import java.util.ArrayList;
import java.util.List;

public class GetReviewsResponse extends GenericResponse {
	String topicName;
	List<ResponseReviewObject> list = new ArrayList<ResponseReviewObject>();

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public List<ResponseReviewObject> getList() {
		return list;
	}

	public void setList(List<ResponseReviewObject> list) {
		this.list = list;
	}
}
