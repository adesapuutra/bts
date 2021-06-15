/**
 * created by : Ryan Ade Saputra
 * created at : 15-06-2021
 */
package com.ryan.bts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryan.bts.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
}
