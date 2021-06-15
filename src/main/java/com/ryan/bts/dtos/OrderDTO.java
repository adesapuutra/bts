/**
 * created by : Ryan Ade Saputra
 * created at : 15-06-2021
 */
package com.ryan.bts.dtos;

import java.util.HashSet;
import java.util.Set;

import com.ryan.bts.models.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDTO {
	private String name;
	private Set<Item> item = new HashSet<Item>();
}
