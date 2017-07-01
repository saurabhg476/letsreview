package com.project.letsreview.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.project.letsreview.api.request.PostReviewsRequest;
import com.project.letsreview.api.response.GenericSuccessResponse;
import com.project.letsreview.api.response.GetReviewsResponse;
import com.project.letsreview.api.response.ResponseReviewObject;
import com.project.letsreview.datamodel.entity.Review;
import com.project.letsreview.datamodel.entity.Topic;
import com.project.letsreview.datamodel.entity.User;
import com.project.letsreview.datamodel.entity.UserSession;
import com.project.letsreview.datamodel.repository.ReviewDAOService;
import com.project.letsreview.datamodel.repository.TopicDAOService;
import com.project.letsreview.datamodel.repository.UserDAOService;
import com.project.letsreview.datamodel.repository.UserSessionDAOService;
import com.project.letsreview.exceptions.CustomGenericException;

@Controller
@ResponseBody
@RequestMapping(value = "/reviews")
public class ReviewsController {

	private static final Logger requestLogger = LoggerFactory.getLogger("request_logger");

	@Autowired
	private ReviewDAOService reviewDAO;

	@Autowired
	private TopicDAOService topicDAO;

	@Autowired
	private UserDAOService userDAO;

	@Autowired
	private UserSessionDAOService userSessionDAO;

	Gson gson = new Gson();

	@RequestMapping(value = "/{topicName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getReviews(HttpServletRequest httpRequest,
			@PathVariable("topicName") String topicName, @RequestParam("page") int page,
			@RequestParam("per_page") int perPage)
			throws InterruptedException {
		
		requestLogger.info("GET-REVIEWS:" + " topicName=" + topicName + " page=" + page + " per_page=" + perPage);

		Topic topic = topicDAO.findTopicByName(topicName);
		if (topic == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}

		List<Review> reviews = reviewDAO.findReviewsByTopicName(topicName, perPage, page * perPage);
		
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
		response.setTopicSummary(topic.getSummary());
		response.setCode("00");
		response.setStatus("SUCCESS");

		String responseString = gson.toJson(response);
		return new ResponseEntity<String>(responseString, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createReview(HttpServletRequest httpRequest,
			@RequestBody @Valid PostReviewsRequest postReviewsRequest) throws CustomGenericException {

		String username = postReviewsRequest.getUsername();
		String sessionToken = postReviewsRequest.getSession_token();
		String topicName = postReviewsRequest.getTopic_name();
		String body = postReviewsRequest.getBody();
		int rating = postReviewsRequest.getRating();

		UserSession userSession = userSessionDAO.findOneByUsername(username);
		if (userSession == null) {
			throw new CustomGenericException("2005");
		}

		if (!sessionToken.equals(userSession.getSessionToken())) {
			throw new CustomGenericException("2006");
		}
		
		if (reviewDAO.findReviewByUserNameAndTopicName(username, topicName) != null) {
			throw new CustomGenericException("2007");
		}

		reviewDAO.createReview(body, username, rating, topicName);
		GenericSuccessResponse response = new GenericSuccessResponse();
		response.setMessage("review has been created successfully");
		return new ResponseEntity<String>(gson.toJson(response), HttpStatus.OK);

	}
}
