package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name="status")
public class CustomerStatus {
	

	@Id
	long id;
	@Column(name="checkin")
	private boolean checkIn;
	@Column(name="checkOut")
	private boolean checkOut;
	@Column(name="forget")
	private boolean forgetParcel;
	@Column(name="leave")
	private boolean leaveParcel;
	@NotEmpty
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	@Column(name="pickedUp")
	private boolean pickedUp;
	
	public CustomerStatus(long id, boolean checkIn, boolean checkOut, boolean forgetParcel, Customer customer, boolean pickedUp, boolean leaveParcel) {
		super();
		this.id = id;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.forgetParcel = forgetParcel;
		this.customer = customer;
		this.pickedUp = pickedUp;
		this.leaveParcel = leaveParcel;
	}
	
	public CustomerStatus() {
		super();
	}

}
