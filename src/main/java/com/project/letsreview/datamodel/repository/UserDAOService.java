package com.project.letsreview.datamodel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.letsreview.datamodel.entity.User;

public interface UserDAOService extends CrudRepository<User, Long> {

	public List<User> findAll();

	@Query(nativeQuery = true, value = "select * from user where username = ?1")
	public User findUserByUsername(String username);

	@Query(nativeQuery = true, value = "select * from user where phone_no = ?1")
	public User findUserByPhoneNo(String phoneNo);

	@Query(nativeQuery = true, value = "select * from user where email_id = ?1")
	public User findUserByEmailId(String emailId);
}
