package com.saurabh.letsreview.datamodel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.saurabh.letsreview.datamodel.entity.Review;

public interface ReviewDAOService extends CrudRepository<Review, Long> {
	List<Review> findAll();

	@Query(nativeQuery = true, value = "select * from review where review.topic_id = (select id from topic where topic.name = ?1)")
	public List<Review> findReviewsByTopicName(String topicName);
}
