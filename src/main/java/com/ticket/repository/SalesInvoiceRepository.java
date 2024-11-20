package com.ticket.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticket.model.SalesInvoice;

public interface SalesInvoiceRepository extends CrudRepository<SalesInvoice, String> {

	SalesInvoice findByponumber(String ponumber);

}
