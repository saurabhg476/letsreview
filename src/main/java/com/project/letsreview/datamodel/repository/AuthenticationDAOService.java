package com.project.letsreview.datamodel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.letsreview.datamodel.entity.Authentication;

public interface AuthenticationDAOService extends CrudRepository<Authentication, Long> {

	@Query(nativeQuery = true, value = "select * from authentication where username = ?1")
	public Authentication findOneByUsername(String username);
}
