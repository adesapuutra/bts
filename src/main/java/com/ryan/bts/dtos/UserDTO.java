/**
 * created by : Ryan Ade Saputra
 * created at : 15-06-2021
 */
package com.ryan.bts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String username;
	private String name;
}
