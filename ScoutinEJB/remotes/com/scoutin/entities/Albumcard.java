package com.scoutin.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AlbumCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ALBUM_CARD", catalog = "Scoutin")
public class AlbumCard implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private AlbumCardId id;

	// Constructors

	/** default constructor */
	public AlbumCard() {
	}

	/** full constructor */
	public AlbumCard(AlbumCardId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "albumId", column = @Column(name = "ALBUM_ID", nullable = false)),
			@AttributeOverride(name = "cardId", column = @Column(name = "CARD_ID", nullable = false)) })
	public AlbumCardId getId() {
		return this.id;
	}

	public void setId(AlbumCardId id) {
		this.id = id;
	}

}