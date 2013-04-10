package com.scoutin.entities;

// Generated Apr 10, 2013 1:11:48 AM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Category generated by hbm2java
 */
public class Category implements java.io.Serializable {

	private short categoryId;
	private String name;
	private Set cards = new HashSet(0);

	public Category() {
	}

	public Category(short categoryId) {
		this.categoryId = categoryId;
	}

	public Category(short categoryId, String name, Set cards) {
		this.categoryId = categoryId;
		this.name = name;
		this.cards = cards;
	}

	public short getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(short categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getCards() {
		return this.cards;
	}

	public void setCards(Set cards) {
		this.cards = cards;
	}

}
