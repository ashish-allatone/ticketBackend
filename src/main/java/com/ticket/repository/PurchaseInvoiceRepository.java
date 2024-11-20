package com.ticket.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticket.model.PurchaseInvoice;

public interface PurchaseInvoiceRepository extends CrudRepository<PurchaseInvoice, String> {

	PurchaseInvoice findByponumber(String ponumber);

}
