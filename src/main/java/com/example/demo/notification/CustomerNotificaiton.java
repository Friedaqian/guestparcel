package com.example.demo.notification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerStatus;
import com.example.demo.repository.customerRepository;
import com.example.demo.repository.statusRepository;

@Component
public class CustomerNotificaiton {
	@Autowired
	customerRepository customerR;
	@Autowired
	statusRepository status;
	
	@Scheduled(cron="0 30 21 * * ?")
	public void sendNotificationToCustomer() {
		List<CustomerStatus> cs = new ArrayList<>();
		List<Customer> c = new ArrayList<>();	
		status.findAll().forEach(s-> cs.add(s));
		for(CustomerStatus customerstatus: cs) {
			if(customerstatus.isPickedUp() == false) {
				long cId = customerstatus.getCustomer().getId();
				Customer customer = this.customerR.findById(cId).get();
				c.add(customer);
			}	
		}		
		for(Customer customer : c) {
			System.out.println("send SMS to" + customer.getPhone());
		}		
	}
	
	public void sendNotificationToParcelForgetten(Customer customer) {		
		System.out.println("send SMS to" + customer.getPhone());
	}
	
}
