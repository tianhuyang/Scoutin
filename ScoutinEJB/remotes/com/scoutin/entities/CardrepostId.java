package com.scoutin.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CardRepostId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class CardRepostId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long cardBodyId;
	private Integer accountId;

	// Constructors

	/** default constructor */
	public CardRepostId() {
	}

	/** full constructor */
	public CardRepostId(Long cardBodyId, Integer accountId) {
		this.cardBodyId = cardBodyId;
		this.accountId = accountId;
	}

	// Property accessors

	@Column(name = "CARD_BODY_ID", nullable = false)
	public Long getCardBodyId() {
		return this.cardBodyId;
	}

	public void setCardBodyId(Long cardBodyId) {
		this.cardBodyId = cardBodyId;
	}

	@Column(name = "ACCOUNT_ID", nullable = false)
	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CardRepostId))
			return false;
		CardRepostId castOther = (CardRepostId) other;

		return ((this.getCardBodyId() == castOther.getCardBodyId()) || (this
				.getCardBodyId() != null && castOther.getCardBodyId() != null && this
				.getCardBodyId().equals(castOther.getCardBodyId())))
				&& ((this.getAccountId() == castOther.getAccountId()) || (this
						.getAccountId() != null
						&& castOther.getAccountId() != null && this
						.getAccountId().equals(castOther.getAccountId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCardBodyId() == null ? 0 : this.getCardBodyId()
						.hashCode());
		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		return result;
	}

}