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

/**
 * CardBody entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CARD_BODY", catalog = "Scoutin")
public class CardBody implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long cardBodyId;
	private Account account;
	private Integer commentsCount = 0;
	private Integer repostsCount = 0;
	private Integer endorsesCount = 0;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private Double latitude;
	private Double longitude;
	private String address;
	private String url;
	private String title;
	private Set<Card> cards = new HashSet<Card>(0);
	private Set<CardRepost> cardReposts = new HashSet<CardRepost>(0);

	// Constructors

	/** default constructor */
	public CardBody() {
	}

	/** minimal constructor */
	public CardBody(Account account, Integer commentsCount,
			Integer repostsCount, Integer endorsesCount, Timestamp createdTime,
			Timestamp updatedTime, String url, String title) {
		this.account = account;
		this.commentsCount = commentsCount;
		this.repostsCount = repostsCount;
		this.endorsesCount = endorsesCount;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.url = url;
		this.title = title;
	}

	/** full constructor */
	public CardBody(Account account, Integer commentsCount,
			Integer repostsCount, Integer endorsesCount, Timestamp createdTime,
			Timestamp updatedTime, Double latitude, Double longitude,
			String address, String url, String title, Set<Card> cards,
			Set<CardRepost> cardReposts) {
		this.account = account;
		this.commentsCount = commentsCount;
		this.repostsCount = repostsCount;
		this.endorsesCount = endorsesCount;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.url = url;
		this.title = title;
		this.cards = cards;
		this.cardReposts = cardReposts;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CARD_BODY_ID", unique = true, nullable = false)
	public Long getCardBodyId() {
		return this.cardBodyId;
	}

	public void setCardBodyId(Long cardBodyId) {
		this.cardBodyId = cardBodyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

	@Column(name = "URL", nullable = false, length = 65535)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "TITLE", nullable = false, length = 35)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cardBody")
	public Set<Card> getCards() {
		return this.cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cardBody")
	public Set<CardRepost> getCardReposts() {
		return this.cardReposts;
	}

	public void setCardReposts(Set<CardRepost> cardReposts) {
		this.cardReposts = cardReposts;
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