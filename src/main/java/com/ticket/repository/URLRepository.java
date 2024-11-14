package com.ticket.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticket.model.URL;

public interface URLRepository extends CrudRepository<URL, String> {

}
