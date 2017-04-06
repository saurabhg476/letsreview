package com.project.letsreview.datamodel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.letsreview.datamodel.entity.ErrorCode;

public interface ErrorCodeDAOService extends CrudRepository<ErrorCode, Long> {

	@Query(nativeQuery = true, value = "select * from error_codes where code = ?1")
	public ErrorCode findOneByCode(String code);
}
