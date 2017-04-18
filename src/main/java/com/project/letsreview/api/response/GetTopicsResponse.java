package com.project.letsreview.api.response;

import java.util.List;

public class GetTopicsResponse extends GenericResponse {

	private List<TopicResponseObject> topicsList;

	public List<TopicResponseObject> getTopicsList() {
		return topicsList;
	}

	public void setTopicsList(List<TopicResponseObject> list) {
		this.topicsList = list;
	}

	public static class TopicResponseObject {
		private String name;
		private String summary;

		public String getName() {
			return name;
		}

		public void setName(String topicName) {
			this.name = topicName;
		}

		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}
	}

}
