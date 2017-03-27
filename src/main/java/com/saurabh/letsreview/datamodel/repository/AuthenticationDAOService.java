package com.saurabh.letsreview.datamodel.repository;

import org.springframework.data.repository.CrudRepository;

import com.saurabh.letsreview.datamodel.entity.Authentication;

public interface AuthenticationDAOService extends CrudRepository<Authentication, Long> {

}
