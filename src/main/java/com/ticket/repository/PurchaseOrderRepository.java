package com.ticket.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticket.model.PurchaseOrder;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, String> {

	PurchaseOrder findByponumber(String ponumber);

}
