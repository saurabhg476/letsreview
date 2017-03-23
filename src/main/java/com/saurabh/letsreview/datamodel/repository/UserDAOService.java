package com.saurabh.letsreview.datamodel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.saurabh.letsreview.datamodel.entity.User;

public interface UserDAOService extends CrudRepository<User, Long> {

	public List<User> findAll();
}
