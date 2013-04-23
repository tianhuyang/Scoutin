package com.scoutin.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCOUNT", catalog = "Scoutin", uniqueConstraints = {
		@UniqueConstraint(columnNames = "FACEBOOK_ID"),
		@UniqueConstraint(columnNames = "EMAIL"),
		@UniqueConstraint(columnNames = "TWITTER_ID") })
public class Account implements java.io.Serializable {

	// Fields

	private Integer accountId;
	private String password;
	private String email;
	private String facebookId;
	private String twitterId;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private String firstname;
	private String lastname;
	private Short sex;
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Album> albums = new HashSet<Album>(0);
	private Set<Cardreposts> cardrepostses = new HashSet<Cardreposts>(0);
	private Accountstat accountstat;
	private Profile profile;
	private Set<Follower> followersForFollowingId = new HashSet<Follower>(0);
	private Set<Card> cards = new HashSet<Card>(0);
	private Set<Cardbody> cardbodies = new HashSet<Cardbody>(0);
	private Set<Follower> followersForFollowedId = new HashSet<Follower>(0);

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(String password, String firstname, String lastname, Short sex) {
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.sex = sex;
	}

	/** full constructor */
	public Account(String password, String email, String facebookId,
			String twitterId, Timestamp createdTime, Timestamp updatedTime,
			String firstname, String lastname, Short sex,
			Set<Comment> comments, Set<Album> albums,
			Set<Cardreposts> cardrepostses, Accountstat accountstat,
			Profile profile, Set<Follower> followersForFollowingId,
			Set<Card> cards, Set<Cardbody> cardbodies,
			Set<Follower> followersForFollowedId) {
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
		this.cardbodies = cardbodies;
		this.followersForFollowedId = followersForFollowedId;
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

	@Column(name = "PASSWORD", nullable = false, length = 15)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL", unique = true)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "FACEBOOK_ID", unique = true, length = 20)
	public String getFacebookId() {
		return this.facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	@Column(name = "TWITTER_ID", unique = true, length = 20)
	public String getTwitterId() {
		return this.twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	@Column(name = "CREATED_TIME", length = 19)
	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "UPDATED_TIME", length = 19)
	public Timestamp getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name = "FIRSTNAME", nullable = false, length = 35)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "LASTNAME", nullable = false, length = 35)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "SEX", nullable = false)
	public Short getSex() {
		return this.sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	public Set<Cardreposts> getCardrepostses() {
		return this.cardrepostses;
	}

	public void setCardrepostses(Set<Cardreposts> cardrepostses) {
		this.cardrepostses = cardrepostses;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
	public Accountstat getAccountstat() {
		return this.accountstat;
	}

	public void setAccountstat(Accountstat accountstat) {
		this.accountstat = accountstat;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountByFollowingId")
	public Set<Follower> getFollowersForFollowingId() {
		return this.followersForFollowingId;
	}

	public void setFollowersForFollowingId(Set<Follower> followersForFollowingId) {
		this.followersForFollowingId = followersForFollowingId;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accounts")
	public Set<Card> getCards() {
		return this.cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	public Set<Cardbody> getCardbodies() {
		return this.cardbodies;
	}

	public void setCardbodies(Set<Cardbody> cardbodies) {
		this.cardbodies = cardbodies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountByFollowedId")
	public Set<Follower> getFollowersForFollowedId() {
		return this.followersForFollowedId;
	}

	public void setFollowersForFollowedId(Set<Follower> followersForFollowedId) {
		this.followersForFollowedId = followersForFollowedId;
	}

}