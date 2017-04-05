package com.project.letsreview.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.project.letsreview.api.request.PostTopicRequest;
import com.project.letsreview.api.response.GenericResponse;
import com.project.letsreview.api.response.GenericSuccessResponse;
import com.project.letsreview.datamodel.entity.Topic;
import com.project.letsreview.datamodel.repository.TopicDAOService;

@Controller
@ResponseBody
@RequestMapping(value = "/topic")
public class TopicController {

	@Autowired
	TopicDAOService topicDAO;

	Gson gson = new Gson();

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> createTopic(@RequestBody String jsonRequest) {

		PostTopicRequest postTopicRequest = gson.fromJson(jsonRequest, PostTopicRequest.class);
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
}
