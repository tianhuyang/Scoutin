package com.scoutin.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AccountClusterId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AccountClusterId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long clusterId;
	private Integer accountId;

	// Constructors

	/** default constructor */
	public AccountClusterId() {
	}

	/** full constructor */
	public AccountClusterId(Long clusterId, Integer accountId) {
		this.clusterId = clusterId;
		this.accountId = accountId;
	}

	// Property accessors

	@Column(name = "CLUSTER_ID", nullable = false)
	public Long getClusterId() {
		return this.clusterId;
	}

	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
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
		if (!(other instanceof AccountClusterId))
			return false;
		AccountClusterId castOther = (AccountClusterId) other;

		return ((this.getClusterId() == castOther.getClusterId()) || (this
				.getClusterId() != null && castOther.getClusterId() != null && this
				.getClusterId().equals(castOther.getClusterId())))
				&& ((this.getAccountId() == castOther.getAccountId()) || (this
						.getAccountId() != null
						&& castOther.getAccountId() != null && this
						.getAccountId().equals(castOther.getAccountId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getClusterId() == null ? 0 : this.getClusterId().hashCode());
		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		return result;
	}

}