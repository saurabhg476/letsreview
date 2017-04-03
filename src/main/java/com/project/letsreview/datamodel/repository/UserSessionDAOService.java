package com.project.letsreview.datamodel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.letsreview.datamodel.entity.UserSession;

public interface UserSessionDAOService extends CrudRepository<UserSession, Long> {

	@Query(nativeQuery = true, value = "select * from user_session where username = ?1")
	public UserSession findOneByUsername(String username);
}
