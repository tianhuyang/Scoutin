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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Cardbody entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CARDBODY", catalog = "Scoutin")
public class Cardbody implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long cardbodyId;
	private Long version;
	private Account account;
	private Integer rating;
	private Integer commentsCount = 0;
	private Integer repostsCount = 0;
	private Integer likesCount = 0;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private Double latitude;
	private Double longitude;
	private String address;
	private String url;
	private String title;
	private Integer ratingCount = 0;
	private Set<Card> cards = new HashSet<Card>(0);
	private Set<Cardrepost> cardreposts = new HashSet<Cardrepost>(0);

	// Constructors

	/** default constructor */
	public Cardbody() {
	}

	/** minimal constructor */
	public Cardbody(Account account, Integer commentsCount,
			Integer repostsCount, Integer likesCount, Timestamp createdTime,
			Timestamp updatedTime) {
		this.account = account;
		this.commentsCount = commentsCount;
		this.repostsCount = repostsCount;
		this.likesCount = likesCount;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	/** full constructor */
	public Cardbody(Account account, Integer rating, Integer commentsCount,
			Integer repostsCount, Integer likesCount, Timestamp createdTime,
			Timestamp updatedTime, Double latitude, Double longitude,
			String address, String url, String title, Integer ratingCount,
			Set<Card> cards, Set<Cardrepost> cardreposts) {
		this.account = account;
		this.rating = rating;
		this.commentsCount = commentsCount;
		this.repostsCount = repostsCount;
		this.likesCount = likesCount;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.url = url;
		this.title = title;
		this.ratingCount = ratingCount;
		this.cards = cards;
		this.cardreposts = cardreposts;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CARDBODY_ID", unique = true, nullable = false)
	public Long getCardbodyId() {
		return this.cardbodyId;
	}

	public void setCardbodyId(Long cardbodyId) {
		this.cardbodyId = cardbodyId;
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
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

	@Column(name = "REPOSTS_COUNT", nullable = false, insertable = false, updatable = false)
	public Integer getRepostsCount() {
		return this.repostsCount;
	}

	public void setRepostsCount(Integer repostsCount) {
		this.repostsCount = repostsCount;
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

	@Column(name = "LATITUDE", precision = 10, scale = 6)
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "LONGITUDE", precision = 10, scale = 6)
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "URL", length = 65535)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "TITLE", length = 35)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "RATING_COUNT", insertable = false, updatable = false)
	public Integer getRatingCount() {
		return this.ratingCount;
	}

	public void setRatingCount(Integer ratingCount) {
		this.ratingCount = ratingCount;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cardbody")
	public Set<Card> getCards() {
		return this.cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cardbody")
	public Set<Cardrepost> getCardreposts() {
		return this.cardreposts;
	}

	public void setCardreposts(Set<Cardrepost> cardreposts) {
		this.cardreposts = cardreposts;
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