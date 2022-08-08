package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Parcel;

@Repository
public interface parcelRepository extends CrudRepository<Parcel,Long>{
	public List<Parcel> findParcelsByCustomerId(long customerId);
}

