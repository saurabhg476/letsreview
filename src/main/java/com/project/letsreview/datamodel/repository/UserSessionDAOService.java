package com.project.letsreview.datamodel.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.letsreview.datamodel.entity.UserSession;

public interface UserSessionDAOService extends CrudRepository<UserSession, Long> {

}
