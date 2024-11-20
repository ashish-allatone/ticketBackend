package com.ticket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ticket.model.CompanyDetails;

@Repository
public interface CompanyDetailsRepository extends CrudRepository<CompanyDetails, Integer> {

//	CompanyDetails findBygstnumber(String gstnumber);

	CompanyDetails findByCompanyId(String companyId);

}
