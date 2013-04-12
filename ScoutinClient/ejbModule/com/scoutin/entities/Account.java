package com.scoutin.entities;

// Generated Apr 11, 2013 7:59:48 AM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Account generated by hbm2java
 */
public class Account implements java.io.Serializable {

	private int accountId;
	private String password;
	private String email;
	private String facebookId;
	private String twitterId;
	private Date createdTime;
	private Date updatedTime;
	private String firstname;
	private String lastname;
	private byte sex;
	private Set comments = new HashSet(0);
	private Set albums = new HashSet(0);
	private Set cardrepostses = new HashSet(0);
	private Accountstat accountstat;
	private Profile profile;
	private Set followersForFollowingId = new HashSet(0);
	private Set cards = new HashSet(0);
	private Set followersForFollowedId = new HashSet(0);

	public Account() {
	}

	public Account(int accountId, String password, String firstname,
			String lastname, byte sex) {
		this.accountId = accountId;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.sex = sex;
	}

	public Account(int accountId, String password, String email,
			String facebookId, String twitterId, Date createdTime,
			Date updatedTime, String firstname, String lastname, byte sex,
			Set comments, Set albums, Set cardrepostses,
			Accountstat accountstat, Profile profile,
			Set followersForFollowingId, Set cards, Set followersForFollowedId) {
		this.accountId = accountId;
		this.password = password;
		this.email = email;
		this.facebookId = facebookId;
		this.twitterId = twitterId;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.firstname = firstname;
		this.lastname = lastname;
		this.sex = sex;
		this.comments = comments;
		this.albums = albums;
		this.cardrepostses = cardrepostses;
		this.accountstat = accountstat;
		this.profile = profile;
		this.followersForFollowingId = followersForFollowingId;
		this.cards = cards;
		this.followersForFollowedId = followersForFollowedId;
	}

	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebookId() {
		return this.facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getTwitterId() {
		return this.twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
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

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public byte getSex() {
		return this.sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set albums) {
		this.albums = albums;
	}

	public Set getCardrepostses() {
		return this.cardrepostses;
	}

	public void setCardrepostses(Set cardrepostses) {
		this.cardrepostses = cardrepostses;
	}

	public Accountstat getAccountstat() {
		return this.accountstat;
	}

	public void setAccountstat(Accountstat accountstat) {
		this.accountstat = accountstat;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Set getFollowersForFollowingId() {
		return this.followersForFollowingId;
	}

	public void setFollowersForFollowingId(Set followersForFollowingId) {
		this.followersForFollowingId = followersForFollowingId;
	}

	public Set getCards() {
		return this.cards;
	}

	public void setCards(Set cards) {
		this.cards = cards;
	}

	public Set getFollowersForFollowedId() {
		return this.followersForFollowedId;
	}

	public void setFollowersForFollowedId(Set followersForFollowedId) {
		this.followersForFollowedId = followersForFollowedId;
	}

}
