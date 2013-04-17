package com.scoutin.entities;

// Generated Apr 16, 2013 2:22:33 AM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Card generated by hbm2java
 */
public class Card implements java.io.Serializable {

	private Long cardId;
	private Cardbody cardbody;
	private String title;
	private String description;
	private Integer rating;
	private Integer commentsCount;
	private Integer repostsCount;
	private Integer likesCount;
	private Date createdTime;
	private Date updatedTime;
	private String tag;
	private Integer ratingCount;
	private Set albums = new HashSet(0);
	private Set accounts = new HashSet(0);
	private Set categories = new HashSet(0);
	private Set cardrepostses = new HashSet(0);
	private Set comments = new HashSet(0);

	public Card() {
	}

	public Card(Cardbody cardbody) {
		this.cardbody = cardbody;
	}

	public Card(Cardbody cardbody, String title, String description,
			Integer rating, Integer commentsCount, Integer repostsCount,
			Integer likesCount, Date createdTime, Date updatedTime, String tag,
			Integer ratingCount, Set albums, Set accounts, Set categories,
			Set cardrepostses, Set comments) {
		this.cardbody = cardbody;
		this.title = title;
		this.description = description;
		this.rating = rating;
		this.commentsCount = commentsCount;
		this.repostsCount = repostsCount;
		this.likesCount = likesCount;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.tag = tag;
		this.ratingCount = ratingCount;
		this.albums = albums;
		this.accounts = accounts;
		this.categories = categories;
		this.cardrepostses = cardrepostses;
		this.comments = comments;
	}

	public Long getCardId() {
		return this.cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Cardbody getCardbody() {
		return this.cardbody;
	}

	public void setCardbody(Cardbody cardbody) {
		this.cardbody = cardbody;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getRatingCount() {
		return this.ratingCount;
	}

	public void setRatingCount(Integer ratingCount) {
		this.ratingCount = ratingCount;
	}

	public Set getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set albums) {
		this.albums = albums;
	}

	public Set getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set accounts) {
		this.accounts = accounts;
	}

	public Set getCategories() {
		return this.categories;
	}

	public void setCategories(Set categories) {
		this.categories = categories;
	}

	public Set getCardrepostses() {
		return this.cardrepostses;
	}

	public void setCardrepostses(Set cardrepostses) {
		this.cardrepostses = cardrepostses;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

}
