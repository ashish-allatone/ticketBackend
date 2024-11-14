package com.ticket.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticket.model.Sales;

public interface SalesRepository extends CrudRepository<Sales, String> {

	Sales findByponumber(String ponumber);

}
