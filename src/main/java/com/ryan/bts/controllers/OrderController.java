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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.bts.dtos.OrderDTO;
import com.ryan.bts.models.Order;
import com.ryan.bts.repositories.OrderRepository;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderRepository orderRepository;
	ModelMapper	modelMapper = new ModelMapper();
	
	public List<Order> listAllOrder(){
		return orderRepository.findAll();
	}
	
	@GetMapping("/get")
	public Map<String, Object> readAllOrder(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<OrderDTO> listOrderDTO = new ArrayList<OrderDTO>();
		List<Order> listOrder= listAllOrder();
		
		listOrder.forEach(item -> {
			OrderDTO dto = modelMapper.map(item, OrderDTO.class);
			
			listOrderDTO.add(dto);
		});

		result.put("status", 200);
		result.put("message", "Success.");
		result.put("status", listOrderDTO);
		
		return result;
	}
}
