package com.scoutin.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * Recommendation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RECOMMENDATION", catalog = "Scoutin")
public class Recommendation implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long recommendationId;
	private Card card;
	private Account account;
	private Short isViewed = 0;
	private Timestamp createdTime;

	// Constructors

	/** default constructor */
	public Recommendation() {
	}

	/** full constructor */
	public Recommendation(Card card, Account account, Short isViewed,
			Timestamp createdTime) {
		this.card = card;
		this.account = account;
		this.isViewed = isViewed;
		this.createdTime = createdTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RECOMMENDATION_ID", unique = true, nullable = false)
	public Long getRecommendationId() {
		return this.recommendationId;
	}

	public void setRecommendationId(Long recommendationId) {
		this.recommendationId = recommendationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARD_ID", nullable = false)
	public Card getCard() {
		return this.card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Column(name = "IS_VIEWED", nullable = false)
	public Short getIsViewed() {
		return this.isViewed;
	}

	public void setIsViewed(Short isViewed) {
		this.isViewed = isViewed;
	}

	@Column(name = "CREATED_TIME", nullable = false, updatable = false, length = 19)
	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@PrePersist
	protected void onPrePersist() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		setCreatedTime(timestamp);
	}

}