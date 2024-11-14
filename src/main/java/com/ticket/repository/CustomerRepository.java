package com.ticket.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticket.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

	Customer findBycompanyName(String name);

}
