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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
	private static final long serialVersionUID = 1L;
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
	private Set<Recommendation> recommendations = new HashSet<Recommendation>(0);
	private Set<CardRepost> cardReposts = new HashSet<CardRepost>(0);
	private AccountStat accountStat;
	private Profile profile;
	private Set<Follower> followersForFollowingId = new HashSet<Follower>(0);
	private Set<CardEndorse> cardEndorses = new HashSet<CardEndorse>(0);
	private Set<AccountCluster> accountClusters = new HashSet<AccountCluster>(0);
	private Set<Cluster> clusters = new HashSet<Cluster>(0);
	private Set<CardBody> cardBodies = new HashSet<CardBody>(0);
	private Set<Follower> followersForFollowedId = new HashSet<Follower>(0);

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(String password, Timestamp createdTime,
			Timestamp updatedTime, String firstname, String lastname, Short sex) {
		this.password = password;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.firstname = firstname;
		this.lastname = lastname;
		this.sex = sex;
	}

	/** full constructor */
	public Account(String password, String email, String facebookId,
			String twitterId, Timestamp createdTime, Timestamp updatedTime,
			String firstname, String lastname, Short sex,
			Set<Comment> comments, Set<Album> albums,
			Set<Recommendation> recommendations, Set<CardRepost> cardReposts,
			AccountStat accountStat, Profile profile,
			Set<Follower> followersForFollowingId,
			Set<CardEndorse> cardEndorses, Set<AccountCluster> accountClusters,
			Set<Cluster> clusters, Set<CardBody> cardBodies,
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
		this.recommendations = recommendations;
		this.cardReposts = cardReposts;
		this.accountStat = accountStat;
		this.profile = profile;
		this.followersForFollowingId = followersForFollowingId;
		this.cardEndorses = cardEndorses;
		this.accountClusters = accountClusters;
		this.clusters = clusters;
		this.cardBodies = cardBodies;
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

	@Column(name = "CREATED_TIME", nullable = false, updatable = false, length = 19)
	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "UPDATED_TIME", nullable = false, length = 19)
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
	public Set<Recommendation> getRecommendations() {
		return this.recommendations;
	}

	public void setRecommendations(Set<Recommendation> recommendations) {
		this.recommendations = recommendations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	public Set<CardRepost> getCardReposts() {
		return this.cardReposts;
	}

	public void setCardReposts(Set<CardRepost> cardReposts) {
		this.cardReposts = cardReposts;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
	public AccountStat getAccountStat() {
		return this.accountStat;
	}

	public void setAccountStat(AccountStat accountStat) {
		this.accountStat = accountStat;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	public Set<CardEndorse> getCardEndorses() {
		return this.cardEndorses;
	}

	public void setCardEndorses(Set<CardEndorse> cardEndorses) {
		this.cardEndorses = cardEndorses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	public Set<AccountCluster> getAccountClusters() {
		return this.accountClusters;
	}

	public void setAccountClusters(Set<AccountCluster> accountClusters) {
		this.accountClusters = accountClusters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	public Set<Cluster> getClusters() {
		return this.clusters;
	}

	public void setClusters(Set<Cluster> clusters) {
		this.clusters = clusters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	public Set<CardBody> getCardBodies() {
		return this.cardBodies;
	}

	public void setCardBodies(Set<CardBody> cardBodies) {
		this.cardBodies = cardBodies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountByFollowedId")
	public Set<Follower> getFollowersForFollowedId() {
		return this.followersForFollowedId;
	}

	public void setFollowersForFollowedId(Set<Follower> followersForFollowedId) {
		this.followersForFollowedId = followersForFollowedId;
	}

	@PrePersist
	protected void onPrePersist() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		setCreatedTime(timestamp);
		setUpdatedTime(timestamp);
	}

	@PreUpdate
	protected void onPreUpdate() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		setUpdatedTime(timestamp);
	}

}