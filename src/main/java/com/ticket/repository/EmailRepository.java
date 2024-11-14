package com.ticket.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ticket.model.Email;

public interface EmailRepository extends CrudRepository<Email, String> {


	List<Email> findBygroupedBy(String groupName);



}
