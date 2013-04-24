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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Album entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ALBUM", catalog = "Scoutin")
public class Album implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long albumId;
	private Account account;
	private String name;
	private String coverPath;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private Integer followCount;
	private Set<Follower> followers = new HashSet<Follower>(0);
	private Set<Card> cards = new HashSet<Card>(0);

	// Constructors

	/** default constructor */
	public Album() {
	}

	/** full constructor */
	public Album(Account account, String name, String coverPath,
			Timestamp createdTime, Timestamp updatedTime, Integer followCount,
			Set<Follower> followers, Set<Card> cards) {
		this.account = account;
		this.name = name;
		this.coverPath = coverPath;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.followCount = followCount;
		this.followers = followers;
		this.cards = cards;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ALBUM_ID", unique = true, nullable = false)
	public Long getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID")
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Column(name = "NAME", length = 35)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "COVER_PATH")
	public String getCoverPath() {
		return this.coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
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

	@Column(name = "FOLLOW_COUNT")
	public Integer getFollowCount() {
		return this.followCount;
	}

	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "albums")
	public Set<Follower> getFollowers() {
		return this.followers;
	}

	public void setFollowers(Set<Follower> followers) {
		this.followers = followers;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "albums")
	public Set<Card> getCards() {
		return this.cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

}