package com.innogent.training.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "emp_gen")
	@SequenceGenerator(name = "emp_gen", sequenceName = "employee_seq", initialValue = 1001, allocationSize = 1)
	private Long empId;

	@Column
	private String empName;

	@Column
	private String job;

	@Column
	private Double sal;

	@Column
	private String address;
}
