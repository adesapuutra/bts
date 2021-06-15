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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.bts.dtos.ItemDTO;
import com.ryan.bts.models.Item;
import com.ryan.bts.repositories.ItemRepository;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	ItemRepository itemRepository;
	ModelMapper modelMapper = new ModelMapper();
	
	public List<Item> listAllItem(){
		return itemRepository.findAll();
	}

	@PostMapping("/add")
	public Map<String, Object> addItem (@RequestBody ItemDTO body){
		Map<String, Object> result = new HashMap<String, Object>();
		long id = 0;
		int stock = body.getStock();
		boolean isExist = false;
		for (Item list : listAllItem()) {
			if (list.getName().equalsIgnoreCase(body.getName())) {
				id = list.getItemId();
				stock += list.getStock();
				isExist = true;
			}
		}
		
		Item item = Item.builder()
				.itemId(isExist ? id : null) // ternary
				.name(body.getName())
				.stock(stock)
				.build();
		itemRepository.save(item);
		
		result.put("Message", "Success.");
		result.put("Data", body);
		
		return result;
	}
	
	@GetMapping("/get")
	public Map<String, Object> readAllItem(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<ItemDTO> listItemDTO = new ArrayList<ItemDTO>();
		List<Item> listItem = listAllItem();
		
		listItem.forEach(item -> {
			ItemDTO dto = modelMapper.map(item, ItemDTO.class);
			
			listItemDTO.add(dto);
		});

		result.put("status", 200);
		result.put("message", "Success.");
		result.put("status", listItemDTO);
		
		return result;
	}
	
}