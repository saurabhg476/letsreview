package com.saurabh.letsreview.controllers;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping(value = "/reviews")
public class ReviewsController {

	@RequestMapping(value = "/{topicName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String process(HttpServletRequest httpRequest, @PathVariable("topicName") String topicName)
			throws InterruptedException {
		JSONObject object = new JSONObject();
		object.put("topicName", topicName);
		return object.toString();
	}
}
