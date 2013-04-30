package com.scoutin.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Albumcard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ALBUMCARD", catalog = "Scoutin")
public class Albumcard implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private AlbumcardId id;
	private Card card;
	private Album album;

	// Constructors

	/** default constructor */
	public Albumcard() {
	}

	/** full constructor */
	public Albumcard(AlbumcardId id, Card card, Album album) {
		this.id = id;
		this.card = card;
		this.album = album;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "albumId", column = @Column(name = "ALBUM_ID", nullable = false)),
			@AttributeOverride(name = "cardId", column = @Column(name = "CARD_ID", nullable = false)) })
	public AlbumcardId getId() {
		return this.id;
	}

	public void setId(AlbumcardId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARD_ID", nullable = false, insertable = false, updatable = false)
	public Card getCard() {
		return this.card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ALBUM_ID", nullable = false, insertable = false, updatable = false)
	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}