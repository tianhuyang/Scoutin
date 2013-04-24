package com.scoutin.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Accountstat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCOUNTSTAT", catalog = "Scoutin")
public class Accountstat implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer accountId;
	private Account account;
	private Integer followingCount;
	private Integer followersCount;
	private Timestamp lastRecmdView;
	private Integer unviewRecmdCount;

	// Constructors

	/** default constructor */
	public Accountstat() {
	}

	/** minimal constructor */
	public Accountstat(Account account) {
		this.account = account;
	}

	/** full constructor */
	public Accountstat(Account account, Integer followingCount,
			Integer followersCount, Timestamp lastRecmdView,
			Integer unviewRecmdCount) {
		this.account = account;
		this.followingCount = followingCount;
		this.followersCount = followersCount;
		this.lastRecmdView = lastRecmdView;
		this.unviewRecmdCount = unviewRecmdCount;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ACCOUNT_ID", unique = true, nullable = false)
	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Column(name = "FOLLOWING_COUNT")
	public Integer getFollowingCount() {
		return this.followingCount;
	}

	public void setFollowingCount(Integer followingCount) {
		this.followingCount = followingCount;
	}

	@Column(name = "FOLLOWERS_COUNT")
	public Integer getFollowersCount() {
		return this.followersCount;
	}

	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}

	@Column(name = "LAST_RECMD_VIEW", length = 19)
	public Timestamp getLastRecmdView() {
		return this.lastRecmdView;
	}

	public void setLastRecmdView(Timestamp lastRecmdView) {
		this.lastRecmdView = lastRecmdView;
	}

	@Column(name = "UNVIEW_RECMD_COUNT")
	public Integer getUnviewRecmdCount() {
		return this.unviewRecmdCount;
	}

	public void setUnviewRecmdCount(Integer unviewRecmdCount) {
		this.unviewRecmdCount = unviewRecmdCount;
	}

}