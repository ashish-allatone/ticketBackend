package com.ticket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ticket.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	public User findByEmail(String emp);

	public User findByEmployeeId(String employeeId);

	public User findByUserName(String userName);


}
