package com.scoutin.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Cardlike entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CARDLIKE", catalog = "Scoutin")
public class Cardlike implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private CardlikeId id;

	// Constructors

	/** default constructor */
	public Cardlike() {
	}

	/** full constructor */
	public Cardlike(CardlikeId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "accountId", column = @Column(name = "ACCOUNT_ID", nullable = false)),
			@AttributeOverride(name = "cardId", column = @Column(name = "CARD_ID", nullable = false)) })
	public CardlikeId getId() {
		return this.id;
	}

	public void setId(CardlikeId id) {
		this.id = id;
	}

}