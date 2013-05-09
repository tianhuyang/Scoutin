package com.scoutin.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * Follower entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FOLLOWER", catalog = "Scoutin")
public class Follower implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private FollowerId id;
	private Account accountByFollowedId;
	private Account accountByFollowingId;
	private Timestamp createdTime;
	private Set<BlockedAlbum> blockedAlbums = new HashSet<BlockedAlbum>(0);

	// Constructors

	/** default constructor */
	public Follower() {
	}

	/** minimal constructor */
	public Follower(FollowerId id, Account accountByFollowedId,
			Account accountByFollowingId, Timestamp createdTime) {
		this.id = id;
		this.accountByFollowedId = accountByFollowedId;
		this.accountByFollowingId = accountByFollowingId;
		this.createdTime = createdTime;
	}

	/** full constructor */
	public Follower(FollowerId id, Account accountByFollowedId,
			Account accountByFollowingId, Timestamp createdTime,
			Set<BlockedAlbum> blockedAlbums) {
		this.id = id;
		this.accountByFollowedId = accountByFollowedId;
		this.accountByFollowingId = accountByFollowingId;
		this.createdTime = createdTime;
		this.blockedAlbums = blockedAlbums;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "followedId", column = @Column(name = "FOLLOWED_ID", nullable = false)),
			@AttributeOverride(name = "followingId", column = @Column(name = "FOLLOWING_ID", nullable = false)) })
	public FollowerId getId() {
		return this.id;
	}

	public void setId(FollowerId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FOLLOWED_ID", nullable = false, insertable = false, updatable = false)
	public Account getAccountByFollowedId() {
		return this.accountByFollowedId;
	}

	public void setAccountByFollowedId(Account accountByFollowedId) {
		this.accountByFollowedId = accountByFollowedId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FOLLOWING_ID", nullable = false, insertable = false, updatable = false)
	public Account getAccountByFollowingId() {
		return this.accountByFollowingId;
	}

	public void setAccountByFollowingId(Account accountByFollowingId) {
		this.accountByFollowingId = accountByFollowingId;
	}

	@Column(name = "CREATED_TIME", nullable = false, updatable = false, length = 19)
	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "follower")
	public Set<BlockedAlbum> getBlockedAlbums() {
		return this.blockedAlbums;
	}

	public void setBlockedAlbums(Set<BlockedAlbum> blockedAlbums) {
		this.blockedAlbums = blockedAlbums;
	}

	@PrePersist
	protected void onPrePersist() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		setCreatedTime(timestamp);
	}

}