package com.tejas.ams.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.ams.user.dto.LoginDto;
import com.tejas.ams.user.dto.UserDto;
import com.tejas.ams.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	
	//list users
	@GetMapping("/list")
	public ResponseEntity<List<UserDto>> getUsers(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getUsers());
	}
	
	//Registration of User
	@PostMapping("/register")
	public ResponseEntity<Map<String,Object>> userRegistration(@RequestBody UserDto userDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.registerUser(userDto));
	}
	
	//login user
	@PostMapping("/login")
	public ResponseEntity<Map<String,Object>> userLogin(@RequestBody LoginDto loginDto){
		return ResponseEntity.status(HttpStatus.OK).body(service.login(loginDto));
	}

	@GetMapping("/check-username")
	public ResponseEntity<Map<String, Object>> checkUsernameAvailability(@RequestParam String username) {
		return ResponseEntity.status(HttpStatus.OK).body(service.checkUsernameAvailability(username));
	}

	/* 
	
	@GetMapping("/check-email")
	public ResponseEntity<Map<String, Object>> checkEmailExistence(@RequestParam String email) {
		return ResponseEntity.status(HttpStatus.OK).body(service.emailExistsCheck(email));
	}
		
	*/
}
