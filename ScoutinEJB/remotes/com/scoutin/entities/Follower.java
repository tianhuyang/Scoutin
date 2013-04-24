package com.scoutin.entities;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	private Boolean isFollowPerson;
	private Set<Album> albums = new HashSet<Album>(0);

	// Constructors

	/** default constructor */
	public Follower() {
	}

	/** minimal constructor */
	public Follower(FollowerId id, Account accountByFollowedId,
			Account accountByFollowingId, Boolean isFollowPerson) {
		this.id = id;
		this.accountByFollowedId = accountByFollowedId;
		this.accountByFollowingId = accountByFollowingId;
		this.isFollowPerson = isFollowPerson;
	}

	/** full constructor */
	public Follower(FollowerId id, Account accountByFollowedId,
			Account accountByFollowingId, Boolean isFollowPerson,
			Set<Album> albums) {
		this.id = id;
		this.accountByFollowedId = accountByFollowedId;
		this.accountByFollowingId = accountByFollowingId;
		this.isFollowPerson = isFollowPerson;
		this.albums = albums;
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

	@Column(name = "IS_FOLLOW_PERSON", nullable = false)
	public Boolean getIsFollowPerson() {
		return this.isFollowPerson;
	}

	public void setIsFollowPerson(Boolean isFollowPerson) {
		this.isFollowPerson = isFollowPerson;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "FOLLOWALBUM", catalog = "Scoutin", joinColumns = {
			@JoinColumn(name = "FOLLOWED_ID", nullable = false, updatable = false),
			@JoinColumn(name = "FOLLOWING_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ALBUM_ID", nullable = false, updatable = false) })
	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

}