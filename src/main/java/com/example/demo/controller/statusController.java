package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerStatus;
import com.example.demo.notification.CustomerNotificaiton;
import com.example.demo.repository.customerRepository;
import com.example.demo.service.CustomerStatusService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/")
@Api(value="information of customer parcel status")
@Slf4j
public class statusController {
	@Autowired
	CustomerStatusService customerstatus;
	@Autowired
	customerRepository customerrepo;
	@Autowired
	CustomerNotificaiton notification;

	@GetMapping("customer/{id}/check")
	@ApiOperation(value="check customer status", notes="check customer status by customer id")
	public ResponseEntity<CustomerStatus> checkCustomerStatusById(@PathVariable long id) throws Exception {	
		CustomerStatus status = customerstatus.getCustomerStatusById(id);	
		if (status!= null) {
			return ResponseEntity.status(200).body(status);
		} else
			return ResponseEntity.status(404).build();
	}
	
	@PostMapping("customer/{id}/modify")
	@ApiOperation(value="modify customer status", notes="modify customer status by customer id")
	public ResponseEntity<CustomerStatus> modifyCustomerStatusByName(@PathVariable long id, @RequestBody@ApiParam(value="modified status",required=true) CustomerStatus status) throws Exception {	
		try {
		     Customer customer = customerrepo.findById(id).get();
		     if(customer!=null) {
		        customerstatus.modifyStatus(id, status);
		        return ResponseEntity.status(HttpStatus.CREATED).body(status);
		     }
		}catch(Exception e) {
			log.error(e.getMessage());	
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}
	
	@PostMapping("customer/{id}/status")
	@ApiOperation(value="init customer status", notes="init customer status after customer is checkin")
	public ResponseEntity<CustomerStatus> initCustomerStatus(@PathVariable long id) throws Exception {
		try {
		     Customer customer = customerrepo.findById(id).get();
		     //default isForget is false, if customer has forgotten parcels, modify status in true
		     CustomerStatus status = new CustomerStatus(id, true, false, false, customer, false, false);
		     customerstatus.initCustomerStatus(status);	
		     if(status.isForgetParcel()) {
		    	 //ToDo: such as AWS step Function, Lambda, automation process
		    	 notification.sendNotificationToParcelForgetten(customer);
		     }
		     return ResponseEntity.status(HttpStatus.CREATED).body(customerstatus.initCustomerStatus(status));
		}catch(Exception e) {
			log.error(e.getMessage());	
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}	

}


