package com.scoutin.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AlbumcardId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AlbumcardId implements java.io.Serializable {

	// Fields

	private Long albumId;
	private Long cardId;

	// Constructors

	/** default constructor */
	public AlbumcardId() {
	}

	/** full constructor */
	public AlbumcardId(Long albumId, Long cardId) {
		this.albumId = albumId;
		this.cardId = cardId;
	}

	// Property accessors

	@Column(name = "ALBUM_ID", nullable = false)
	public Long getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	@Column(name = "CARD_ID", nullable = false)
	public Long getCardId() {
		return this.cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AlbumcardId))
			return false;
		AlbumcardId castOther = (AlbumcardId) other;

		return ((this.getAlbumId() == castOther.getAlbumId()) || (this
				.getAlbumId() != null && castOther.getAlbumId() != null && this
				.getAlbumId().equals(castOther.getAlbumId())))
				&& ((this.getCardId() == castOther.getCardId()) || (this
						.getCardId() != null && castOther.getCardId() != null && this
						.getCardId().equals(castOther.getCardId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAlbumId() == null ? 0 : this.getAlbumId().hashCode());
		result = 37 * result
				+ (getCardId() == null ? 0 : this.getCardId().hashCode());
		return result;
	}

}