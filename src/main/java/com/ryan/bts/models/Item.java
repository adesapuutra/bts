package com.ryan.bts.models;
// Generated Jun 15, 2021, 10:58:05 AM by Hibernate Tools 5.1.10.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;

/**
 * Item generated by hbm2java
 */
@Entity
@Table(name = "item", schema = "public")
@Builder
public class Item implements java.io.Serializable {

	private long itemId;
	private String name;
	private Integer stock;
	private Set<Order> orders = new HashSet<Order>(0);

	public Item() {
	}

	public Item(long itemId) {
		this.itemId = itemId;
	}

	public Item(long itemId, String name, Integer stock, Set<Order> orders) {
		this.itemId = itemId;
		this.name = name;
		this.stock = stock;
		this.orders = orders;
	}

	@Id

	@Column(name = "item_id", unique = true, nullable = false)
	public long getItemId() {
		return this.itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	@Column(name = "name", length = 25)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "stock")
	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}