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

/**
 * Card entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CARD", catalog = "Scoutin")
public class Card implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long cardId;
	private CardBody cardBody;
	private String description;
	private Integer rating;
	private Integer commentsCount = 0;
	private Integer endorsesCount = 0;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private String tag;
	private Set<Album> albums = new HashSet<Album>(0);
	private Set<Recommendation> recommendations = new HashSet<Recommendation>(0);
	private Set<CardEndorse> cardEndorses = new HashSet<CardEndorse>(0);
	private Set<Category> categories = new HashSet<Category>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);

	// Constructors

	/** default constructor */
	public Card() {
	}

	/** minimal constructor */
	public Card(CardBody cardBody, Integer rating, Integer commentsCount,
			Integer endorsesCount, Timestamp createdTime, Timestamp updatedTime) {
		this.cardBody = cardBody;
		this.rating = rating;
		this.commentsCount = commentsCount;
		this.endorsesCount = endorsesCount;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	/** full constructor */
	public Card(CardBody cardBody, String description, Integer rating,
			Integer commentsCount, Integer endorsesCount,
			Timestamp createdTime, Timestamp updatedTime, String tag,
			Set<Album> albums, Set<Recommendation> recommendations,
			Set<CardEndorse> cardEndorses, Set<Category> categories,
			Set<Comment> comments) {
		this.cardBody = cardBody;
		this.description = description;
		this.rating = rating;
		this.commentsCount = commentsCount;
		this.endorsesCount = endorsesCount;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.tag = tag;
		this.albums = albums;
		this.recommendations = recommendations;
		this.cardEndorses = cardEndorses;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARD_BODY_ID", nullable = false)
	public CardBody getCardBody() {
		return this.cardBody;
	}

	public void setCardBody(CardBody cardBody) {
		this.cardBody = cardBody;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "RATING", nullable = false)
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

	@Column(name = "ENDORSES_COUNT", nullable = false, insertable = false, updatable = false)
	public Integer getEndorsesCount() {
		return this.endorsesCount;
	}

	public void setEndorsesCount(Integer endorsesCount) {
		this.endorsesCount = endorsesCount;
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

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ALBUM_CARD", catalog = "Scoutin", joinColumns = { @JoinColumn(name = "CARD_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ALBUM_ID", nullable = false, updatable = false) })
	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "card")
	public Set<Recommendation> getRecommendations() {
		return this.recommendations;
	}

	public void setRecommendations(Set<Recommendation> recommendations) {
		this.recommendations = recommendations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "card")
	public Set<CardEndorse> getCardEndorses() {
		return this.cardEndorses;
	}

	public void setCardEndorses(Set<CardEndorse> cardEndorses) {
		this.cardEndorses = cardEndorses;
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