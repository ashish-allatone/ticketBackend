package com.ticket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import com.ticket.model.User;
import com.ticket.model.ServiceRequest;

@Repository
public interface ServiceRequestRepository extends CrudRepository<ServiceRequest, Integer> {




	
	



	
	

//	public List<ServiceRequest> findBySr_no(String sr_no);



	

	


	//ServiceRequest saveCreateRequest(ServiceRequest createSR);

}
