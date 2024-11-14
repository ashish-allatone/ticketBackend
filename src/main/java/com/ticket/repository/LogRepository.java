package com.ticket.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticket.model.Log;

public interface LogRepository extends CrudRepository<Log, String> {

	Log findByurl(String url);

}
