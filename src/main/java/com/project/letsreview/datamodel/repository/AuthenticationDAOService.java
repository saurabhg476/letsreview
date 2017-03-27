package com.project.letsreview.datamodel.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.letsreview.datamodel.entity.Authentication;

public interface AuthenticationDAOService extends CrudRepository<Authentication, Long> {

}
