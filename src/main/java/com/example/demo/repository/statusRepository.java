package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CustomerStatus;

@Repository
public interface statusRepository extends CrudRepository<CustomerStatus,Long>{
	public CustomerStatus findCustomerStatusById(long customerId);
}
