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
 * CardRepost entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CARD_REPOST", catalog = "Scoutin")
public class CardRepost implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private CardRepostId id;
	private CardBody cardBody;
	private Account account;
	private Integer count = 0;

	// Constructors

	/** default constructor */
	public CardRepost() {
	}

	/** full constructor */
	public CardRepost(CardRepostId id, CardBody cardBody, Account account,
			Integer count) {
		this.id = id;
		this.cardBody = cardBody;
		this.account = account;
		this.count = count;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "cardBodyId", column = @Column(name = "CARD_BODY_ID", nullable = false)),
			@AttributeOverride(name = "accountId", column = @Column(name = "ACCOUNT_ID", nullable = false)) })
	public CardRepostId getId() {
		return this.id;
	}

	public void setId(CardRepostId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARD_BODY_ID", nullable = false, insertable = false, updatable = false)
	public CardBody getCardBody() {
		return this.cardBody;
	}

	public void setCardBody(CardBody cardBody) {
		this.cardBody = cardBody;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false, insertable = false, updatable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Column(name = "COUNT", nullable = false, insertable = false, updatable = false)
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}