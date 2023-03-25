package com.example.hithron.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.hithron.api.dao.UserRepository;
import com.example.hithron.api.dto.UserDTO;
import com.example.hithron.api.dto.UserUpdateDTO;
import com.example.hithron.api.exception.UserCustomException;
import com.example.hithron.api.exception.UserNotFoundException;
import com.example.hithron.api.model.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
		
	public UserEntity createUser(UserDTO userDto) {
		UserEntity user = UserEntity.build(new Integer(0),userDto.getFirstName(), userDto.getLastName(), userDto.getAge(), userDto.getDateOfBirth(), userDto.getGender(), userDto.getType(), userDto.getLine1(), userDto.getLine2(), userDto.getPostCode(), userDto.getCity(),userDto.getState());
		
		return userRepository.save(user);
	}
	
	public UserEntity getUserByUserId(Integer userId, Boolean addressRequired) throws UserNotFoundException {	
		
		UserEntity userEntity =  userRepository.findByUserId(userId);
		if(userEntity == null) throw new UserNotFoundException("User Not Found"); 
		if(addressRequired) {
			return userEntity;
		} else {			
			userEntity.setType(null);
			userEntity.setCity(null);
			userEntity.setState(null);
			userEntity.setLine1(null);
			userEntity.setLine2(null);
			userEntity.setPostCode(null);
			return userEntity;
		}
	}
	
	public UserEntity updateUser(UserUpdateDTO userDto) throws UserCustomException, UserNotFoundException {		
		UserEntity user = getUserByUserId(userDto.getUserId(), true);
		
		if (!user.getDateOfBirth().equalsIgnoreCase(userDto.getDateOfBirth())) {			
			throw new UserCustomException("DOB can't be updated");
		}
		user.setAge(userDto.getAge());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setGender(userDto.getGender());	
		return userRepository.save(user);
	}

}
