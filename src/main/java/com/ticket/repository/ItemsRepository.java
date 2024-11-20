package com.ticket.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticket.model.Items;

public interface ItemsRepository extends CrudRepository<Items, String> {

	Items findByname(String name);



}
