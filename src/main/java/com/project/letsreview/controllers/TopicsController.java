package com.project.letsreview.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.project.letsreview.api.request.PostTopicRequest;
import com.project.letsreview.api.response.GenericResponse;
import com.project.letsreview.api.response.GenericSuccessResponse;
import com.project.letsreview.api.response.GetTopicsResponse;
import com.project.letsreview.api.response.GetTopicsResponse.TopicResponseObject;
import com.project.letsreview.datamodel.entity.Topic;
import com.project.letsreview.datamodel.repository.TopicDAOService;

@Controller
@ResponseBody
@RequestMapping(value = "/topics")
public class TopicsController {

	@Autowired
	TopicDAOService topicDAO;

	Gson gson = new Gson();

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> createTopic(@RequestBody @Valid PostTopicRequest postTopicRequest) {

		String name = postTopicRequest.getName();

		Topic topic = topicDAO.findTopicByName(name);
		if (topic != null) {
			GenericResponse response = new GenericResponse();
			response.setCode("2007");
			response.setMessage("Topic already exists");
			response.setStatus("FAIL");
			return new ResponseEntity<String>(gson.toJson(response), HttpStatus.OK);
		}

		topic = new Topic();
		topic.setName(postTopicRequest.getName());
		topic.setDescription(postTopicRequest.getDescription());

		topicDAO.save(topic);

		GenericSuccessResponse response = new GenericSuccessResponse();
		response.setMessage("Topic has been created successfully");

		return new ResponseEntity<String>(gson.toJson(response), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> getTopics(@RequestParam("q") String query) {

		List<TopicResponseObject> topicList = new ArrayList<TopicResponseObject>();
		List<Topic> topics = topicDAO.findTopics(query);

		for (Topic topic : topics) {
			TopicResponseObject topicResponseObject = new TopicResponseObject();
			topicResponseObject.setName(topic.getName());
			topicResponseObject.setSummary(topic.getSummary());
			topicList.add(topicResponseObject);
		}

		GetTopicsResponse response = new GetTopicsResponse();
		response.setCode("00");
		response.setStatus("SUCCESS");
		response.setTopicsList(topicList);

		return new ResponseEntity<String>(gson.toJson(response), HttpStatus.OK);
	}

}
