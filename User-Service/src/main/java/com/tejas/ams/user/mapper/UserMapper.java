package com.tejas.ams.user.mapper;

import org.springframework.stereotype.Component;

import com.tejas.ams.user.dto.UserDto;
import com.tejas.ams.user.entity.User;

@Component
public class UserMapper {
	
	public User toEntity(UserDto dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setRole(dto.getRole());
		user.setCustomerCategory(dto.getCustomerCategory());
		user.setPhone(dto.getPhone());
		user.setEmail(dto.getEmail());
		user.setAddress(dto.getAddress());
		user.setDateOfBirth(dto.getDateOfBirth());
		return user;
	}
	
	public UserDto toDto(User user) {
		UserDto dto = new UserDto();
		dto.setName(user.getName());
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		dto.setRole(user.getRole());
		dto.setCustomerCategory(user.getCustomerCategory());
		dto.setPhone(user.getPhone());
		dto.setEmail(user.getEmail());
		dto.setAddress(user.getAddress());
		dto.setDateOfBirth(user.getDateOfBirth());
		return dto;
	}
	
}
