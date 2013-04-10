package com.scoutin.entities;

// Generated Apr 10, 2013 1:11:48 AM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cardbody generated by hbm2java
 */
public class Cardbody implements java.io.Serializable {

	private long cardbodyId;
	private Cardstat cardstat;
	private Integer rating;
	private Integer commentsCount;
	private Integer repostsCount;
	private Integer likesCount;
	private Date createdTime;
	private Date updatedTime;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String address;
	private Set cards = new HashSet(0);

	public Cardbody() {
	}

	public Cardbody(long cardbodyId) {
		this.cardbodyId = cardbodyId;
	}

	public Cardbody(long cardbodyId, Cardstat cardstat, Integer rating,
			Integer commentsCount, Integer repostsCount, Integer likesCount,
			Date createdTime, Date updatedTime, BigDecimal latitude,
			BigDecimal longitude, String address, Set cards) {
		this.cardbodyId = cardbodyId;
		this.cardstat = cardstat;
		this.rating = rating;
		this.commentsCount = commentsCount;
		this.repostsCount = repostsCount;
		this.likesCount = likesCount;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.cards = cards;
	}

	public long getCardbodyId() {
		return this.cardbodyId;
	}

	public void setCardbodyId(long cardbodyId) {
		this.cardbodyId = cardbodyId;
	}

	public Cardstat getCardstat() {
		return this.cardstat;
	}

	public void setCardstat(Cardstat cardstat) {
		this.cardstat = cardstat;
	}

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getCommentsCount() {
		return this.commentsCount;
	}

	public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}

	public Integer getRepostsCount() {
		return this.repostsCount;
	}

	public void setRepostsCount(Integer repostsCount) {
		this.repostsCount = repostsCount;
	}

	public Integer getLikesCount() {
		return this.likesCount;
	}

	public void setLikesCount(Integer likesCount) {
		this.likesCount = likesCount;
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

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set getCards() {
		return this.cards;
	}

	public void setCards(Set cards) {
		this.cards = cards;
	}

}
