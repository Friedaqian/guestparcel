package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Parcel;
import com.example.demo.repository.parcelRepository;
import com.example.demo.repository.statusRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ParcelService {
	
	@Autowired
	private parcelRepository parcelRepo;
	@Autowired
	private statusRepository status;
	
	public List<Parcel> getAllCheckedParcels(){
		List<Parcel> allparcel = new ArrayList<>();
		parcelRepo.findAll().forEach(parcel->allparcel.add(parcel));
		if(allparcel.size()<=0) log.warn("no parcels");
		return allparcel;	
	}
	
	public List<Parcel> findAllCheckedParcelsByCustomerID(long customerId) throws Exception {
		List<Parcel> allparcel = new ArrayList<>();
		if(customerId != 0) {
		   parcelRepo.findParcelsByCustomerId(customerId).forEach(parcel->allparcel.add(parcel));
		}
		else {
			log.error("there is no parcels under id");
			throw new Exception("no parcels under id found");
			
		}
		return allparcel;		
	}
	
	
	public Parcel saveParcel(Parcel e) {
		return parcelRepo.save(e);
		
	}
	
	public void deleteParcelsByStatus() {
		
	}

}
