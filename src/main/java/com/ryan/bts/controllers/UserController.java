/**
 * created by : Ryan Ade Saputra
 * created at : 15-06-2021
 */
package com.ryan.bts.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.bts.dtos.UserDTO;
import com.ryan.bts.exception.ResourceNotFoundException;
import com.ryan.bts.models.User;
import com.ryan.bts.repositories.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	ModelMapper	modelMapper = new ModelMapper();
	
	public List<User> listAllUser(){
		return userRepository.findAll();
	}
	
	@PostMapping("/add")
	public Map<String, Object> addUser (@RequestBody UserDTO body){
		Map<String, Object> result = new HashMap<String, Object>();
		
		User user = User.builder()
				.name(body.getName())
				.username(body.getUsername())
				.build();
		
		result.put("Message", "Success.");
		result.put("Data", body);
		
		return result;
	}
	
	@GetMapping("/get")
	public Map<String, Object> readAllUser(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<UserDTO> listUserDTO = new ArrayList<UserDTO>();
		List<User> listUser= listAllUser();
		
		listUser.forEach(item -> {
			UserDTO dto = modelMapper.map(item, UserDTO.class);
			listUserDTO.add(dto);
		});

		result.put("status", 200);
		result.put("message", "Success.");
		result.put("status", listUserDTO);
		
		return result;
	}
	
	@PutMapping("/update")
	public Map<String,Object> updateUser(@RequestParam(name = "userid") Long userId, @RequestBody UserDTO body){
		Map<String, Object> result = new HashMap<String, Object>();
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		
		user.setName(body.getName());
		user.setUsername(body.getUsername());
		User updatedUser= userRepository.save(user);
		
		result.put("status", 200);
		result.put("message", "Success.");
		result.put("status", updatedUser);
		
		return result;
	}
}
