package com.saurabh.letsreview.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saurabh.letsreview.datamodel.entity.Review;
import com.saurabh.letsreview.datamodel.repository.ReviewDAOService;
import com.saurabh.letsreview.datamodel.repository.TopicDAOService;

@Controller
@ResponseBody
@RequestMapping(value = "/reviews")
public class ReviewsController {

	@Autowired
	private ReviewDAOService reviewDAO;

	@Autowired
	private TopicDAOService topicDAO;

	@RequestMapping(value = "/{topicName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String process(HttpServletRequest httpRequest, @PathVariable("topicName") String topicName)
			throws InterruptedException {

		JSONObject response = new JSONObject();
		JSONArray responseReviewsList = new JSONArray();

		List<Review> reviews = reviewDAO.findReviewsByTopicName(topicName);

		for (Review review : reviews) {
			JSONObject responseReviewObject = new JSONObject();

			JSONObject responseUserObject = new JSONObject();
			responseUserObject.put("id", review.getUser().getId());
			responseUserObject.put("name", review.getUser().getName());

			responseReviewObject.put("user", responseUserObject);
			responseReviewObject.put("created_on", review.getCreatedOn());
			responseReviewObject.put("rating", review.getRating());
			responseReviewObject.put("body", review.getBody());

			responseReviewsList.put(responseReviewObject);
		}

		response.put("list", responseReviewsList);
		response.put("topic", topicName);

		return response.toString();
	}
}
