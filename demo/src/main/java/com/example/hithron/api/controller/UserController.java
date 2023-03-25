package com.example.hithron.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hithron.api.dao.UserRepository;
import com.example.hithron.api.dto.UserDTO;
import com.example.hithron.api.dto.UserUpdateDTO;
import com.example.hithron.api.exception.UserCustomException;
import com.example.hithron.api.exception.UserNotFoundException;
import com.example.hithron.api.model.UserEntity;
import com.example.hithron.api.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<UserEntity> createUser(@RequestBody @Valid UserDTO user) {		
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}

	
	@GetMapping("/getUserById/{userId}/{addressRequired}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Integer userId, @PathVariable String addressRequired) throws UserNotFoundException {	
		
			return ResponseEntity.ok(userService.getUserByUserId(userId, Boolean.valueOf(addressRequired)));			
	}


	@PutMapping("/updateUserInfo")
	public ResponseEntity<UserEntity> updateUserInfo(@RequestBody  @Valid UserUpdateDTO user) throws UserCustomException, UserNotFoundException {	
		UserEntity dbUser = userService.updateUser(user);
		
		return ResponseEntity.ok(dbUser);
		
	}
	

}
