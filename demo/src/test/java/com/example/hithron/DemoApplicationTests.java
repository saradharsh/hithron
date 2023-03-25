package com.example.hithron;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;


import com.example.hithron.api.dao.UserRepository;
import com.example.hithron.api.dto.UserDTO;

import com.example.hithron.api.model.UserEntity;
import com.example.hithron.api.service.UserService;


@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getUserServiceTest() throws Exception {
		when(userRepository.findByUserId(new Integer("1"))).thenReturn(UserEntity.build(new Integer(1),"sarsava", "chand", new Integer(33), "01-12-1990", "male", "fdf" ,"house", "asdfdsa", "asdfds", "421321", "bangalore"));
		assertEquals(true, userService.getUserByUserId(new Integer(1), true)!=null);
		assertEquals("sarsava", userService.getUserByUserId(new Integer(1), true).getFirstName());
	
		 
	     
	}

	@Test
	public void getUserWebTest() throws Exception {
		
		// GET OPERATION
			when(userRepository.findByUserId(new Integer("1"))).thenReturn(UserEntity.build(new Integer(1),"sarsava", "chand", new Integer(33), "01-12-1990", "male", "fdf" ,"house", "asdfdsa", "asdfds", "421321", "bangalore"));
			 mockMvc.perform(get("http://localhost:8090/getUserById/1/true").accept(MediaType.APPLICATION_JSON))
		        .andDo(print()).andExpect(status().isOk());
		 
			when(userRepository.findByUserId(new Integer("3"))).thenReturn(UserEntity.build(new Integer(1),"sarsava", "chand", new Integer(33), "01-12-1990", "male", "fdf" ,"house", "asdfdsa", "asdfds", "421321", "bangalore"));
			 mockMvc.perform(get("http://localhost:8090/getUserById/2/true").accept(MediaType.APPLICATION_JSON))
		        .andDo(print()).andExpect(status().isNotFound());
			 
		//SAVE OPERATION
			 
			UserEntity userEntity = UserEntity.build(new Integer(5),"tester", "chand", new Integer(33), "01-12-1990", "male", "fdf" ,"house", "asdfdsa", "asdfds", "421321", "bangalore");
			
			UserDTO userDto = UserDTO.build(new Integer(5),"daniel", "chand", new Integer(33), "01-12-1990", "male", "fdf" ,"house", "asdfdsa", "asdfds", "421321", "bangalore");
				when(userRepository.save(userEntity)).thenReturn(UserEntity.build(new Integer(5),"daniel", "chand", new Integer(33), "01-12-1990", "male", "fdf" ,"house", "asdfdsa", "asdfds", "421321", "bangalore"));
				
				when(userRepository.findByUserId(new Integer("5"))).thenReturn(UserEntity.build(new Integer(1),"tester", "chand", new Integer(33), "01-12-1990", "male", "fdf" ,"house", "asdfdsa", "asdfds", "421321", "bangalore"));
				 mockMvc.perform(get("http://localhost:8090/getUserById/5/true").accept(MediaType.APPLICATION_JSON))
			        .andDo(print()).andExpect(status().isOk());
				 assertEquals("tester", userService.getUserByUserId(new Integer(5), true).getFirstName());
				 
		 	 
					when(userRepository.findByUserId(new Integer("5"))).thenReturn(UserEntity.build(new Integer(1),"daniel", "chand", new Integer(33), "01-12-1990", "male", "fdf" ,"house", "asdfdsa", "asdfds", "421321", "bangalore"));
					 mockMvc.perform(get("http://localhost:8090/getUserById/5/true").accept(MediaType.APPLICATION_JSON))
				        .andDo(print()).andExpect(status().isOk());
					
					 
				 
			 
			 
	}

}
