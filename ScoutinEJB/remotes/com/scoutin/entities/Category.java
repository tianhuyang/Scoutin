package com.scoutin.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CATEGORY", catalog = "Scoutin")
public class Category implements java.io.Serializable {

	// Fields

	private Short categoryId;
	private String name;
	private Set<Card> cards = new HashSet<Card>(0);

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** full constructor */
	public Category(String name, Set<Card> cards) {
		this.name = name;
		this.cards = cards;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CATEGORY_ID", unique = true, nullable = false)
	public Short getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Short categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "NAME", length = 35)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "CARDCATEGORY", catalog = "Scoutin", joinColumns = { @JoinColumn(name = "CATEGORY_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "CARD_ID", nullable = false, updatable = false) })
	public Set<Card> getCards() {
		return this.cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

}