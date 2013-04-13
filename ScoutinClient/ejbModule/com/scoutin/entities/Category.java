package com.scoutin.entities;

// Generated Apr 12, 2013 6:29:04 AM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Category generated by hbm2java
 */
public class Category implements java.io.Serializable {

	private Short categoryId;
	private String name;
	private Set cards = new HashSet(0);

	public Category() {
	}

	public Category(String name, Set cards) {
		this.name = name;
		this.cards = cards;
	}

	public Short getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Short categoryId) {
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
