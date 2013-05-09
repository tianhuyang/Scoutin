package com.scoutin.vos.card;

public class RepostCardVO {
	public String description; 
	public Integer rating;
	public Long cardBodyId;
	public Long[] albumIds;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public Long getCardBodyId() {
		return cardBodyId;
	}
	public void setCardBodyId(Long cardBodyId) {
		this.cardBodyId = cardBodyId;
	}
	public Long[] getAlbumIds() {
		return albumIds;
	}
	public void setAlbumIds(Long[] albumIds) {
		this.albumIds = albumIds;
	}
	
	
}
