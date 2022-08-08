package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name="customer")
public class Customer {
	
	private static final long serialVersionUID = 2325330047604532909L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@NotEmpty
	@Column(name="firstname")
	private String firstName;
	@NotEmpty
	@Column(name="familyname")
	private String familyName;
	@NotEmpty
	@Column(name="phone")
	private long phone;

}
