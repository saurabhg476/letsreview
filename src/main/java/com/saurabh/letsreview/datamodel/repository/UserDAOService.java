package com.saurabh.letsreview.datamodel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.saurabh.letsreview.datamodel.entity.User;

public interface UserDAOService extends CrudRepository<User, Long> {

	public List<User> findAll();

	@Query(nativeQuery = true, value = "select * from user where username = ?1")
	public User findUserByUsername(String username);
}
