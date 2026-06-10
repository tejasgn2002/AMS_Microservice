package com.tejas.ams.user.service;

import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.tejas.ams.user.dto.LoginDto;
import com.tejas.ams.user.dto.UserDto;

public interface UserService {
	Map<String,Object> registerUser(UserDto userDto);

	@Nullable
	List<UserDto> getUsers();

	@Nullable
	Map<String,Object> login(LoginDto loginDto);

	@Nullable
	Map<String, Object> checkUsernameAvailability(String username);

	@Nullable
	Map<String, Object> emailExistsCheck(String emailId);
}
