package com.project.letsreview.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.project.letsreview.api.request.PostReviewsRequest;
import com.project.letsreview.api.response.GetReviewsResponse;
import com.project.letsreview.api.response.ResponseReviewObject;
import com.project.letsreview.datamodel.entity.Review;
import com.project.letsreview.datamodel.entity.Topic;
import com.project.letsreview.datamodel.entity.User;
import com.project.letsreview.datamodel.repository.ReviewDAOService;
import com.project.letsreview.datamodel.repository.TopicDAOService;
import com.project.letsreview.datamodel.repository.UserDAOService;

@Controller
@ResponseBody
@RequestMapping(value = "/reviews")
public class ReviewsController {

	@Autowired
	private ReviewDAOService reviewDAO;

	@Autowired
	private TopicDAOService topicDAO;

	@Autowired
	private UserDAOService userDAO;

	Gson gson = new Gson();

	@RequestMapping(value = "/{topicName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getReviews(HttpServletRequest httpRequest, @PathVariable("topicName") String topicName)
			throws InterruptedException {
		
		Topic topic = topicDAO.findTopicByName(topicName);
		if (topic == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}

		List<Review> reviews = reviewDAO.findReviewsByTopicName(topicName);
		
		GetReviewsResponse response = new GetReviewsResponse();

		for (Review review : reviews) {
			ResponseReviewObject responseReviewObject = new ResponseReviewObject();

			responseReviewObject.setRating(review.getRating());
			responseReviewObject.setBody(review.getBody());
			responseReviewObject.setCreated_on(review.getCreatedOn());

			User user = userDAO.findOne(review.getUserId());
			responseReviewObject.getUser().setUsername(user.getUsername());
			responseReviewObject.getUser().setName(user.getName());
			response.getList().add(responseReviewObject);
		}
		
		response.setTopicName(topicName);
		response.setCode("00");
		response.setStatus("SUCCESS");

		String responseString = gson.toJson(response);
		return new ResponseEntity<String>(responseString, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createReview(HttpServletRequest httpRequest, @RequestBody String jsonRequest) {

		PostReviewsRequest postReviewsRequest = gson.fromJson(jsonRequest, PostReviewsRequest.class);
		
		String body = postReviewsRequest.getBody();
		String username = postReviewsRequest.getUsername();
		int rating = postReviewsRequest.getRating();
		String topic_name = postReviewsRequest.getTopic_name();
		
		
		reviewDAO.createReview(body, username, rating, topic_name);
		return new ResponseEntity<String>(HttpStatus.OK);

	}
}
