package com.innogent.training.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "userManagement")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	private String userId;
	private String userName;
	private String password;
	private boolean active;
	private String roles;
	private int age;
	private String homeAddress;
}
