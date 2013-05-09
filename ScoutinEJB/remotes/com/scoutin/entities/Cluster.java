package com.scoutin.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

/**
 * Cluster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CLUSTER", catalog = "Scoutin")
public class Cluster implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long clusterId;
	private Account account;
	private String name;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private Set<AccountCluster> accountClusters = new HashSet<AccountCluster>(0);

	// Constructors

	/** default constructor */
	public Cluster() {
	}

	/** minimal constructor */
	public Cluster(Account account, String name, Timestamp createdTime,
			Timestamp updatedTime) {
		this.account = account;
		this.name = name;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	/** full constructor */
	public Cluster(Account account, String name, Timestamp createdTime,
			Timestamp updatedTime, Set<AccountCluster> accountClusters) {
		this.account = account;
		this.name = name;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.accountClusters = accountClusters;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CLUSTER_ID", unique = true, nullable = false)
	public Long getClusterId() {
		return this.clusterId;
	}

	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OWNER_ID", nullable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CREATED_TIME", nullable = false, updatable = false, length = 19)
	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "UPDATED_TIME", nullable = false, length = 19)
	public Timestamp getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cluster")
	public Set<AccountCluster> getAccountClusters() {
		return this.accountClusters;
	}

	public void setAccountClusters(Set<AccountCluster> accountClusters) {
		this.accountClusters = accountClusters;
	}

	@PrePersist
	protected void onPrePersist() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		setCreatedTime(timestamp);
		setUpdatedTime(timestamp);
	}

	@PreUpdate
	protected void onPreUpdate() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		setUpdatedTime(timestamp);
	}

}