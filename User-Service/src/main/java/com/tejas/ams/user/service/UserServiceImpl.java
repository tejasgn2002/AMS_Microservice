package com.tejas.ams.user.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.tejas.ams.user.dto.LoginDto;
import com.tejas.ams.user.dto.UserDto;
import com.tejas.ams.user.entity.User;
import com.tejas.ams.user.exceptions.GeneralError;
import com.tejas.ams.user.exceptions.InvalidLoginException;
import com.tejas.ams.user.exceptions.UserNotFoundException;
import com.tejas.ams.user.mapper.UserMapper;
import com.tejas.ams.user.repository.UserRepository;
import com.tejas.ams.user.util.ResponseMessageUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepo;
	private final UserMapper mapper;
	private final ResponseMessageUtil responseUtil;
	
	@Override
	public Map<String,Object> registerUser(UserDto userDto) {
		log.info("Request for the User Registration");
		Integer id;
		try{
			id = userRepo.save(mapper.toEntity(userDto)).getUserId();
			
		}catch(Exception e) {
			log.error("Error while saving to database: "+e);
			throw new GeneralError("Something went wrong");
		}
		
		log.info("User registered successfully with ID: "+id);
		
		
		return responseUtil.genericSuccess("message","User registered successfully with ID: "+id);
	}

	@Override
	public @Nullable List<UserDto> getUsers() {
		log.info("Users detalis fetch request");
		
		List<UserDto> usersDto = userRepo.findAll().stream().map(mapper::toDto).toList();
		
		if(usersDto.isEmpty()) {
			log.error("Users Data not found");
			throw new UserNotFoundException("Users Data not found");
		}
		
		log.info("Users details fetched successfully");
		
		return usersDto;
	}

	@Override
	public @Nullable Map<String, Object> login(LoginDto loginDto) {
		log.info("User login request for username: "+loginDto.getUsername());
		Optional<User> optUser = userRepo.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
		if(optUser.isEmpty()) {
			log.warn("Invalid Login Credentials for username: "+loginDto.getUsername());
			throw new InvalidLoginException("Invalid Login Credentials");
		}
		log.info("Login Successfull for username: "+loginDto.getUsername());
		return responseUtil.genericSuccess("message","Login Successfull");
	}
	
	
}
