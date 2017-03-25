package com.saurabh.letsreview.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.saurabh.letsreview.api.response.GetReviewsResponse;
import com.saurabh.letsreview.api.response.ResponseReviewObject;
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

	Gson gson = new Gson();

	@RequestMapping(value = "/{topicName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> process(HttpServletRequest httpRequest, @PathVariable("topicName") String topicName)
			throws InterruptedException {
		
		List<Review> reviews = reviewDAO.findReviewsByTopicName(topicName);
		
		GetReviewsResponse response = new GetReviewsResponse();

		for (Review review : reviews) {
			ResponseReviewObject responseReviewObject = new ResponseReviewObject();

			responseReviewObject.setRating(review.getRating());
			responseReviewObject.setBody(review.getBody());
			responseReviewObject.setCreated_on(review.getCreatedOn());
			responseReviewObject.getUser().setId(review.getUser().getId());
			responseReviewObject.getUser().setName(review.getUser().getName());
			response.getList().add(responseReviewObject);
		}
		
		response.setTopicName(topicName);
		String responseString = gson.toJson(response);
		return new ResponseEntity<String>(responseString, HttpStatus.OK);
	}
}
