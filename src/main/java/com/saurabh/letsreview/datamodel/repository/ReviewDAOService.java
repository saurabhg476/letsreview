package com.saurabh.letsreview.datamodel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.saurabh.letsreview.datamodel.entity.Review;

public interface ReviewDAOService extends CrudRepository<Review, Long> {
	List<Review> findAll();
}
