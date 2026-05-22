package com.tejas.ams.user.dto;

import lombok.Data;

@Data
public class UserDto {
	private String name;
	private String username;
	private String password;
	private String role;
	private String customerCategory;
	private long phone;
	private String email;
	private String address;
	private String dateOfBirth;
}
