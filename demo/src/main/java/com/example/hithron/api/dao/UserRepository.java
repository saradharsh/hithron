package com.example.hithron.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hithron.api.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	UserEntity findByUserId(Integer userId);


	
}
