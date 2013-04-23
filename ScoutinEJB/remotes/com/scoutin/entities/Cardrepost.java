package com.scoutin.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Cardrepost entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CARDREPOST", catalog = "Scoutin")
public class Cardrepost implements java.io.Serializable {

	// Fields

	private CardrepostId id;
	private Card card;
	private Account account;
	private Integer count;

	// Constructors

	/** default constructor */
	public Cardrepost() {
	}

	/** minimal constructor */
	public Cardrepost(CardrepostId id, Card card, Account account) {
		this.id = id;
		this.card = card;
		this.account = account;
	}

	/** full constructor */
	public Cardrepost(CardrepostId id, Card card, Account account, Integer count) {
		this.id = id;
		this.card = card;
		this.account = account;
		this.count = count;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "accountId", column = @Column(name = "ACCOUNT_ID", nullable = false)),
			@AttributeOverride(name = "cardId", column = @Column(name = "CARD_ID", nullable = false)) })
	public CardrepostId getId() {
		return this.id;
	}

	public void setId(CardrepostId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARD_ID", nullable = false, insertable = false, updatable = false)
	public Card getCard() {
		return this.card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false, insertable = false, updatable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Column(name = "COUNT")
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}