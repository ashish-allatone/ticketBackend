package com.ticket.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ticket.model.OtpClass;

@Repository
public interface OtpClassRepository extends CrudRepository<OtpClass, String> {

	Optional<OtpClass> findByEmail(String email);

	Optional<OtpClass> findByUserName(String userName);
	
	//Optional<OtpClass> findByUserName(String userName);

	//Optional<OtpClass> findByEmail(String email);

}
