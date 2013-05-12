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
 * AccountStat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCOUNT_STAT", catalog = "Scoutin")
public class AccountStat implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer accountId;
	private Account account;
	private Integer followingCount = 0;
	private Integer followersCount = 0;
	private Timestamp lastNewsVisited;
	private Integer unviewRecmdCount = 0;

	// Constructors

	/** default constructor */
	public AccountStat() {
	}

	/** minimal constructor */
	public AccountStat(Account account, Integer followingCount,
			Integer followersCount, Integer unviewRecmdCount) {
		this.account = account;
		this.followingCount = followingCount;
		this.followersCount = followersCount;
		this.unviewRecmdCount = unviewRecmdCount;
	}

	/** full constructor */
	public AccountStat(Account account, Integer followingCount,
			Integer followersCount, Timestamp lastNewsVisited,
			Integer unviewRecmdCount) {
		this.account = account;
		this.followingCount = followingCount;
		this.followersCount = followersCount;
		this.lastNewsVisited = lastNewsVisited;
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

	@Column(name = "LAST_NEWS_VISITED", length = 19)
	public Timestamp getLastNewsVisited() {
		return this.lastNewsVisited;
	}

	public void setLastNewsVisited(Timestamp lastNewsVisited) {
		this.lastNewsVisited = lastNewsVisited;
	}

	@Column(name = "UNVIEW_RECMD_COUNT", nullable = false, insertable = false, updatable = false)
	public Integer getUnviewRecmdCount() {
		return this.unviewRecmdCount;
	}

	public void setUnviewRecmdCount(Integer unviewRecmdCount) {
		this.unviewRecmdCount = unviewRecmdCount;
	}

}