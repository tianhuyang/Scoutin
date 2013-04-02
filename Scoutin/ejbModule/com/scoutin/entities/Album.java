package com.scoutin.entities;

// Generated Apr 1, 2013 6:45:42 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Album generated by hbm2java
 */
public class Album implements java.io.Serializable {

	private long albumId;
	private Account account;
	private String name;
	private String coverPath;
	private Date createdTime;
	private Date updatedTime;
	private Integer followCount;
	private Set<Follower> followers = new HashSet<Follower>(0);
	private Set<Card> cards = new HashSet<Card>(0);

	public Album() {
	}

	public Album(long albumId, Account account) {
		this.albumId = albumId;
		this.account = account;
	}

	public Album(long albumId, Account account, String name, String coverPath,
			Date createdTime, Date updatedTime, Integer followCount,
			Set<Follower> followers, Set<Card> cards) {
		this.albumId = albumId;
		this.account = account;
		this.name = name;
		this.coverPath = coverPath;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.followCount = followCount;
		this.followers = followers;
		this.cards = cards;
	}

	public long getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoverPath() {
		return this.coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getFollowCount() {
		return this.followCount;
	}

	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}

	public Set<Follower> getFollowers() {
		return this.followers;
	}

	public void setFollowers(Set<Follower> followers) {
		this.followers = followers;
	}

	public Set<Card> getCards() {
		return this.cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

}
