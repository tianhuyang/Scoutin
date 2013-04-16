package com.scoutin.entities;

// Generated Apr 15, 2013 6:05:19 AM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Album generated by hbm2java
 */
public class Album implements java.io.Serializable {

	private Long albumId;
	private Account account;
	private String name;
	private String coverPath;
	private Date createdTime;
	private Date updatedTime;
	private Integer followCount;
	private Set followers = new HashSet(0);
	private Set cards = new HashSet(0);

	public Album() {
	}

	public Album(Account account, String name, String coverPath,
			Date createdTime, Date updatedTime, Integer followCount,
			Set followers, Set cards) {
		this.account = account;
		this.name = name;
		this.coverPath = coverPath;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.followCount = followCount;
		this.followers = followers;
		this.cards = cards;
	}

	public Long getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(Long albumId) {
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

	public Set getFollowers() {
		return this.followers;
	}

	public void setFollowers(Set followers) {
		this.followers = followers;
	}

	public Set getCards() {
		return this.cards;
	}

	public void setCards(Set cards) {
		this.cards = cards;
	}

}
