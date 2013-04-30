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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Card entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CARD", catalog = "Scoutin")
public class Card implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long cardId;
	private Long version;
	private Cardbody cardbody;
	private String description;
	private Integer rating;
	private Integer commentsCount = 0;
	private Integer likesCount = 0;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private String tag;
	private Integer ratingCount = 0;
	private Set<Album> albums = new HashSet<Album>(0);
	private Set<Account> accounts = new HashSet<Account>(0);
	private Set<Category> categories = new HashSet<Category>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);

	// Constructors

	/** default constructor */
	public Card() {
	}

	/** minimal constructor */
	public Card(Cardbody cardbody, Integer commentsCount, Integer likesCount,
			Timestamp createdTime, Timestamp updatedTime) {
		this.cardbody = cardbody;
		this.commentsCount = commentsCount;
		this.likesCount = likesCount;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	/** full constructor */
	public Card(Cardbody cardbody, String description, Integer rating,
			Integer commentsCount, Integer likesCount, Timestamp createdTime,
			Timestamp updatedTime, String tag, Integer ratingCount,
			Set<Album> albums, Set<Account> accounts, Set<Category> categories,
			Set<Comment> comments) {
		this.cardbody = cardbody;
		this.description = description;
		this.rating = rating;
		this.commentsCount = commentsCount;
		this.likesCount = likesCount;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.tag = tag;
		this.ratingCount = ratingCount;
		this.albums = albums;
		this.accounts = accounts;
		this.categories = categories;
		this.comments = comments;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CARD_ID", unique = true, nullable = false)
	public Long getCardId() {
		return this.cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	@Version
	@Column(name = "VERSION", nullable = false)
	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARDBODY_ID", nullable = false)
	public Cardbody getCardbody() {
		return this.cardbody;
	}

	public void setCardbody(Cardbody cardbody) {
		this.cardbody = cardbody;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "RATING")
	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Column(name = "COMMENTS_COUNT", nullable = false, insertable = false, updatable = false)
	public Integer getCommentsCount() {
		return this.commentsCount;
	}

	public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}

	@Column(name = "LIKES_COUNT", nullable = false, insertable = false, updatable = false)
	public Integer getLikesCount() {
		return this.likesCount;
	}

	public void setLikesCount(Integer likesCount) {
		this.likesCount = likesCount;
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

	@Column(name = "TAG", length = 65535)
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "RATING_COUNT", insertable = false, updatable = false)
	public Integer getRatingCount() {
		return this.ratingCount;
	}

	public void setRatingCount(Integer ratingCount) {
		this.ratingCount = ratingCount;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ALBUMCARD", catalog = "Scoutin", joinColumns = { @JoinColumn(name = "CARD_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ALBUM_ID", nullable = false, updatable = false) })
	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "CARDLIKE", catalog = "Scoutin", joinColumns = { @JoinColumn(name = "CARD_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ACCOUNT_ID", nullable = false, updatable = false) })
	public Set<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cards")
	public Set<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "card")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
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