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
import javax.persistence.Version;

/**
 * Accountstat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCOUNTSTAT", catalog = "Scoutin")
public class Accountstat implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer accountId;
	private Long version;
	private Account account;
	private Integer followingCount = 0;
	private Integer followersCount = 0;
	private Timestamp lastRecmdView;
	private Integer unviewRecmdCount = 0;

	// Constructors

	/** default constructor */
	public Accountstat() {
	}

	/** minimal constructor */
	public Accountstat(Account account, Integer followingCount,
			Integer followersCount, Integer unviewRecmdCount) {
		this.account = account;
		this.followingCount = followingCount;
		this.followersCount = followersCount;
		this.unviewRecmdCount = unviewRecmdCount;
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

	@Version
	@Column(name = "VERSION", nullable = false)
	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Column(name = "FOLLOWING_COUNT", nullable = false, insertable = false, updatable = false)
	public Integer getFollowingCount() {
		return this.followingCount;
	}

	public void setFollowingCount(Integer followingCount) {
		this.followingCount = followingCount;
	}

	@Column(name = "FOLLOWERS_COUNT", nullable = false, insertable = false, updatable = false)
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

	@Column(name = "UNVIEW_RECMD_COUNT", nullable = false, insertable = false, updatable = false)
	public Integer getUnviewRecmdCount() {
		return this.unviewRecmdCount;
	}

	public void setUnviewRecmdCount(Integer unviewRecmdCount) {
		this.unviewRecmdCount = unviewRecmdCount;
	}

}