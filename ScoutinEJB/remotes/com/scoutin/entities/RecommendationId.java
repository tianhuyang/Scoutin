package com.scoutin.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RecommendationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class RecommendationId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer accountId;
	private Long cardId;

	// Constructors

	/** default constructor */
	public RecommendationId() {
	}

	/** full constructor */
	public RecommendationId(Integer accountId, Long cardId) {
		this.accountId = accountId;
		this.cardId = cardId;
	}

	// Property accessors

	@Column(name = "ACCOUNT_ID", nullable = false)
	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@Column(name = "CARD_ID", nullable = false)
	public Long getCardId() {
		return this.cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RecommendationId))
			return false;
		RecommendationId castOther = (RecommendationId) other;

		return ((this.getAccountId() == castOther.getAccountId()) || (this
				.getAccountId() != null && castOther.getAccountId() != null && this
				.getAccountId().equals(castOther.getAccountId())))
				&& ((this.getCardId() == castOther.getCardId()) || (this
						.getCardId() != null && castOther.getCardId() != null && this
						.getCardId().equals(castOther.getCardId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result
				+ (getCardId() == null ? 0 : this.getCardId().hashCode());
		return result;
	}

}