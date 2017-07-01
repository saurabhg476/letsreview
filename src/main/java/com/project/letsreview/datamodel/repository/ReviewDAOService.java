package com.project.letsreview.datamodel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.project.letsreview.datamodel.entity.Review;

public interface ReviewDAOService extends CrudRepository<Review, Long> {
	List<Review> findAll();

	@Query(nativeQuery = true, value = "select * from review where review.topic_id = (select id from topic where topic.name = ?1) limit ?3,?2")
	public List<Review> findReviewsByTopicName(String topicName, int limit, int offset);

	@Transactional(value = "letsReviewTransactionManager")
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO review(body,user_id,rating,created_on,topic_id) VALUES (?1,(select id from user where username = ?2), ?3,now(),(select id from topic where name = ?4))")
	public void createReview(String body, String username, int rating, String topic_name);

	@Query(nativeQuery = true, value = "SELECT FROM review WHERE user_id = ?1 and topic_id = ?2")
	public Review findReviewByUserAndTopic(int userId, int topicId);

	@Query(nativeQuery = true, value = "SELECT * FROM review WHERE user_id = (SELECT id FROM `user` WHERE username= ?1) AND topic_id = "
			+ "(select id from topic where name = ?2)")
	public Review findReviewByUserNameAndTopicName(String userName, String topicName);

}
