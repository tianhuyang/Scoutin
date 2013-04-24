package com.scoutin.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CardrepostId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class CardrepostId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long cardbodyId;
	private Integer accountId;

	// Constructors

	/** default constructor */
	public CardrepostId() {
	}

	/** full constructor */
	public CardrepostId(Long cardbodyId, Integer accountId) {
		this.cardbodyId = cardbodyId;
		this.accountId = accountId;
	}

	// Property accessors

	@Column(name = "CARDBODY_ID", nullable = false)
	public Long getCardbodyId() {
		return this.cardbodyId;
	}

	public void setCardbodyId(Long cardbodyId) {
		this.cardbodyId = cardbodyId;
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
		if (!(other instanceof CardrepostId))
			return false;
		CardrepostId castOther = (CardrepostId) other;

		return ((this.getCardbodyId() == castOther.getCardbodyId()) || (this
				.getCardbodyId() != null && castOther.getCardbodyId() != null && this
				.getCardbodyId().equals(castOther.getCardbodyId())))
				&& ((this.getAccountId() == castOther.getAccountId()) || (this
						.getAccountId() != null
						&& castOther.getAccountId() != null && this
						.getAccountId().equals(castOther.getAccountId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCardbodyId() == null ? 0 : this.getCardbodyId()
						.hashCode());
		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		return result;
	}

}