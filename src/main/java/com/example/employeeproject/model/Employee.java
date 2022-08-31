package com.example.employeeproject.model;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@ApiModelProperty(hidden = true)
	private int id; // Employee Id
	@Column
	@Pattern(regexp = "^[a-zA-Z]*$",message = "Name Should Not have Special Characters and Integers")
	@NotBlank(message = "Employee Name Should Not be Empty&Null")
	private String name; // // Employee name
	@Column
	@NotBlank(message = "Email Should Not be empty&Null")
	@Email(message = "Email Is Not valid")
	@Size(max = 30,min = 3,message = "Email Size Is Invalid")
	private String email; // Employee email
	@Column()
	@NotBlank(message = "PhoneNumber Should Not Be Empty&Null")
	@Size(max = 10,min = 10,message = "Phone Number Is Invalid")
	@Pattern(regexp = "^[0-9]*$",message = "Phone Number Should have 0-9 digits")
	private String phonenumber; // Employee phonenumber
	@Column
	@DecimalMax(value = "10000000.00")
	@DecimalMin(value = "0.00")
	@Digits(integer=20, fraction=2,message = "Salary Should have Fraction=2")
	@Positive
	private BigDecimal salary;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt=new Date();
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt=new Date();
	
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	@PreUpdate
	public void setUpdatedAt() {
		this.updatedAt = new Date();
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	@PrePersist
	public void setCreatedAt() {
		this.createdAt = new Date();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	
	
	public Employee() {
		super();
	}
	public Employee(
			@Pattern(regexp = "^[a-zA-Z]*$", message = "Name Should Not have Special Characters and Integers") @NotBlank(message = "Employee Name Should Not be Empty&Null") String name,
			@NotBlank(message = "Email Should Not be empty&Null") @Email(message = "Email Is Not valid") @Size(max = 30, min = 3, message = "Email Size Is Invalid") String email,
			@NotBlank(message = "PhoneNumber Should Not Be Empty&Null") @Size(max = 10, min = 10, message = "Phone Number Is Invalid") @Pattern(regexp = "^[0-9]*$", message = "Phone Number Should have 0-9 digits") String phonenumber,
			@DecimalMax("10000000.00") @DecimalMin("0.00") @Digits(integer = 20, fraction = 2, message = "Salary Should have Fraction=2") @Positive BigDecimal salary,
			Date createdAt, Date updatedAt) {
		super();
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
		this.salary = salary;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Employee(int id,
			@Pattern(regexp = "^[a-zA-Z]*$", message = "Name Should Not have Special Characters and Integers") @NotBlank(message = "Employee Name Should Not be Empty&Null") String name,
			@NotBlank(message = "Email Should Not be empty&Null") @Email(message = "Email Is Not valid") @Size(max = 30, min = 3, message = "Email Size Is Invalid") String email,
			@NotBlank(message = "PhoneNumber Should Not Be Empty&Null") @Size(max = 10, min = 10, message = "Phone Number Is Invalid") @Pattern(regexp = "^[0-9]*$", message = "Phone Number Should have 0-9 digits") String phonenumber,
			@DecimalMax("10000000.00") @DecimalMin("0.00") @Digits(integer = 20, fraction = 2, message = "Salary Should have Fraction=2") @Positive BigDecimal salary) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
		this.salary = salary;
	}
	
	
	
	

}
