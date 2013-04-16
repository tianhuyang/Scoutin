package com.scoutin.entities;

// Generated Apr 15, 2013 10:24:31 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Follower generated by hbm2java
 */
public class Follower implements java.io.Serializable {

	private FollowerId id;
	private Account accountByFollowedId;
	private Account accountByFollowingId;
	private boolean isFollowPerson;
	private Set albums = new HashSet(0);

	public Follower() {
	}

	public Follower(FollowerId id, Account accountByFollowedId,
			Account accountByFollowingId, boolean isFollowPerson) {
		this.id = id;
		this.accountByFollowedId = accountByFollowedId;
		this.accountByFollowingId = accountByFollowingId;
		this.isFollowPerson = isFollowPerson;
	}

	public Follower(FollowerId id, Account accountByFollowedId,
			Account accountByFollowingId, boolean isFollowPerson, Set albums) {
		this.id = id;
		this.accountByFollowedId = accountByFollowedId;
		this.accountByFollowingId = accountByFollowingId;
		this.isFollowPerson = isFollowPerson;
		this.albums = albums;
	}

	public FollowerId getId() {
		return this.id;
	}

	public void setId(FollowerId id) {
		this.id = id;
	}

	public Account getAccountByFollowedId() {
		return this.accountByFollowedId;
	}

	public void setAccountByFollowedId(Account accountByFollowedId) {
		this.accountByFollowedId = accountByFollowedId;
	}

	public Account getAccountByFollowingId() {
		return this.accountByFollowingId;
	}

	public void setAccountByFollowingId(Account accountByFollowingId) {
		this.accountByFollowingId = accountByFollowingId;
	}

	public boolean isIsFollowPerson() {
		return this.isFollowPerson;
	}

	public void setIsFollowPerson(boolean isFollowPerson) {
		this.isFollowPerson = isFollowPerson;
	}

	public Set getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set albums) {
		this.albums = albums;
	}

}
