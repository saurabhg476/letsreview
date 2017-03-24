package com.saurabh.letsreview.datamodel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.saurabh.letsreview.datamodel.entity.Topic;

public interface TopicDAOService extends CrudRepository<Topic, Long> {
	List<Topic> findAll();

	@Query(nativeQuery = true, value = "select * from topic where name = ?1")
	public Topic findTopicByName(String topicName);
}
