package com.scoutin.entities;

import java.sql.Timestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * AccountCluster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCOUNT_CLUSTER", catalog = "Scoutin")
public class AccountCluster implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private AccountClusterId id;
	private Cluster cluster;
	private Account account;
	private Timestamp createdTime;

	// Constructors

	/** default constructor */
	public AccountCluster() {
	}

	/** full constructor */
	public AccountCluster(AccountClusterId id, Cluster cluster,
			Account account, Timestamp createdTime) {
		this.id = id;
		this.cluster = cluster;
		this.account = account;
		this.createdTime = createdTime;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "clusterId", column = @Column(name = "CLUSTER_ID", nullable = false)),
			@AttributeOverride(name = "accountId", column = @Column(name = "ACCOUNT_ID", nullable = false)) })
	public AccountClusterId getId() {
		return this.id;
	}

	public void setId(AccountClusterId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLUSTER_ID", nullable = false, insertable = false, updatable = false)
	public Cluster getCluster() {
		return this.cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false, insertable = false, updatable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
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