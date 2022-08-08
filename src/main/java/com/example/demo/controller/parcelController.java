package com.example.demo.controller;

import java.util.List;

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
import com.example.demo.entity.Parcel;
import com.example.demo.repository.customerRepository;
import com.example.demo.service.ParcelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/")
@Api(value="information of customer parcels")
@Slf4j
public class parcelController {
	@Autowired
	ParcelService parcel;
	@Autowired
	customerRepository customerrepo;
	
	@GetMapping("customer/{id}/checkparcel")
	@ApiOperation(value="check customer parcels", notes="check customer parcels by customer id")
	public ResponseEntity<List<Parcel>> checkCustomerStatusByName(@PathVariable long id) throws Exception {	
	    List<Parcel> parcels = parcel.findAllCheckedParcelsByCustomerID(id);
		if (parcels.size()>0) {
			return ResponseEntity.status(200).body(parcels);
		} else
			return ResponseEntity.status(404).build();
	}
	
	@GetMapping("parcels")
	@ApiOperation(value="check all parcels", notes="check all parcels")
	public ResponseEntity<List<Parcel>> getAll() {
		List<Parcel> parcels = parcel.getAllCheckedParcels();
		if (parcels.size()>0) {
			return ResponseEntity.status(200).body(parcels);
		} else
			return ResponseEntity.status(404).build();		
	}
	
	@PostMapping("customer/{id}/addparcel")
	@ApiOperation(value="add customer parcels", notes="add customer parcels by customer id")
	public ResponseEntity<Parcel> initCustomerStatus(@PathVariable long id, @RequestBody@ApiParam(value="created parcels object", required=true) Parcel parcels) throws Exception {
		try {
			Customer customer = customerrepo.findById(id).get();
			if(customer != null) {
			parcels.setCustomer(customer);
			parcel.saveParcel(parcels);
			//parcel.saveParcel(parcels);
		    return ResponseEntity.status(HttpStatus.CREATED).body(parcels);
			}
		}catch(Exception e) {
			e.getMessage();
			log.error("Customer cannot be found");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}	
	

}
