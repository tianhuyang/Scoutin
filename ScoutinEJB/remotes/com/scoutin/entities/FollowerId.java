package com.scoutin.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FollowerId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class FollowerId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer followedId;
	private Integer followingId;

	// Constructors

	/** default constructor */
	public FollowerId() {
	}

	/** full constructor */
	public FollowerId(Integer followedId, Integer followingId) {
		this.followedId = followedId;
		this.followingId = followingId;
	}

	// Property accessors

	@Column(name = "FOLLOWED_ID", nullable = false)
	public Integer getFollowedId() {
		return this.followedId;
	}

	public void setFollowedId(Integer followedId) {
		this.followedId = followedId;
	}

	@Column(name = "FOLLOWING_ID", nullable = false)
	public Integer getFollowingId() {
		return this.followingId;
	}

	public void setFollowingId(Integer followingId) {
		this.followingId = followingId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FollowerId))
			return false;
		FollowerId castOther = (FollowerId) other;

		return ((this.getFollowedId() == castOther.getFollowedId()) || (this
				.getFollowedId() != null && castOther.getFollowedId() != null && this
				.getFollowedId().equals(castOther.getFollowedId())))
				&& ((this.getFollowingId() == castOther.getFollowingId()) || (this
						.getFollowingId() != null
						&& castOther.getFollowingId() != null && this
						.getFollowingId().equals(castOther.getFollowingId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getFollowedId() == null ? 0 : this.getFollowedId()
						.hashCode());
		result = 37
				* result
				+ (getFollowingId() == null ? 0 : this.getFollowingId()
						.hashCode());
		return result;
	}

}