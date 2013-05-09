package com.scoutin.entities;

import java.sql.Timestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * BlockedAlbum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BLOCKED_ALBUM", catalog = "Scoutin")
public class BlockedAlbum implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private BlockedAlbumId id;
	private Album album;
	private Follower follower;
	private Timestamp createdTime;

	// Constructors

	/** default constructor */
	public BlockedAlbum() {
	}

	/** full constructor */
	public BlockedAlbum(BlockedAlbumId id, Album album, Follower follower,
			Timestamp createdTime) {
		this.id = id;
		this.album = album;
		this.follower = follower;
		this.createdTime = createdTime;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "albumId", column = @Column(name = "ALBUM_ID", nullable = false)),
			@AttributeOverride(name = "followedId", column = @Column(name = "FOLLOWED_ID", nullable = false)),
			@AttributeOverride(name = "followingId", column = @Column(name = "FOLLOWING_ID", nullable = false)) })
	public BlockedAlbumId getId() {
		return this.id;
	}

	public void setId(BlockedAlbumId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ALBUM_ID", nullable = false, insertable = false, updatable = false)
	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "FOLLOWED_ID", referencedColumnName = "FOLLOWED_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "FOLLOWING_ID", referencedColumnName = "FOLLOWING_ID", nullable = false, insertable = false, updatable = false) })
	public Follower getFollower() {
		return this.follower;
	}

	public void setFollower(Follower follower) {
		this.follower = follower;
	}

	@Column(name = "CREATED_TIME", nullable = false, updatable = false, length = 19)
	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@PrePersist
	protected void onPrePersist() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		setCreatedTime(timestamp);
	}

}