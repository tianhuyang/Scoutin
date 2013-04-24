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
	private static final long serialVersionUID = 1L;
	private CardrepostId id;
	private Account account;
	private Cardbody cardbody;
	private Integer count;

	// Constructors

	/** default constructor */
	public Cardrepost() {
	}

	/** minimal constructor */
	public Cardrepost(CardrepostId id, Account account, Cardbody cardbody) {
		this.id = id;
		this.account = account;
		this.cardbody = cardbody;
	}

	/** full constructor */
	public Cardrepost(CardrepostId id, Account account, Cardbody cardbody,
			Integer count) {
		this.id = id;
		this.account = account;
		this.cardbody = cardbody;
		this.count = count;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "cardbodyId", column = @Column(name = "CARDBODY_ID", nullable = false)),
			@AttributeOverride(name = "accountId", column = @Column(name = "ACCOUNT_ID", nullable = false)) })
	public CardrepostId getId() {
		return this.id;
	}

	public void setId(CardrepostId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false, insertable = false, updatable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARDBODY_ID", nullable = false, insertable = false, updatable = false)
	public Cardbody getCardbody() {
		return this.cardbody;
	}

	public void setCardbody(Cardbody cardbody) {
		this.cardbody = cardbody;
	}

	@Column(name = "COUNT")
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}