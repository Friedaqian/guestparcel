package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerStatus;
import com.example.demo.repository.statusRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerStatusService {
	
	@Autowired
	statusRepository customerstatus;
	
	public CustomerStatus getCustomerStatusById(long id) throws Exception {
		CustomerStatus customer = null;
		if (customerstatus.existsById(id)) {
		    customer = customerstatus.findById(id).get();
		}
		else {
			log.error("Customer is not existed.");
			throw new Exception("Customer is not existed.");		
		}
		return customer;
	}
	
	public void modifyStatus(long id, CustomerStatus modifiedcustomer) throws Exception {
		CustomerStatus customer = null;
		if (customerstatus.existsById(id)) {
		    customer = customerstatus.findById(id).get();	
		    customer = modifiedcustomer;
		    customerstatus.save(customer);
		}
		else {
			log.error("Customer is not existed.");
			throw new Exception("Customer is not existed.");
		}
	}
	
	public CustomerStatus initCustomerStatus(CustomerStatus customer) throws Exception {
		return customerstatus.save(customer);
	}
	
	
}
