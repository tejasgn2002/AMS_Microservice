package com.tejas.ams.user.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_table")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String name;
	@Column(unique = true)
	private String username;
	private String password;
	@Column(name = "role",insertable = false)
	@ColumnDefault("'user'")
	private String role;
	private String customerCategory;
	private long phone;
	@Column(unique = true)
	private String email;
	private String address;
	@Column(name = "date_of_birth",columnDefinition = "DATE")
	private String dateOfBirth;
	
	@Column(name = "is_deleted",insertable = false)
	@ColumnDefault("false")
	private Boolean isDeleted;
	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;
}
