package com.scoutin.vos.card;

public class SaveCardVO {
	
	public Long cardId;
	public String description;
	public Integer rating;
	public String tag;
	public Long version; 

	public Short[] categoryIds;
	public Long[] albumIds;
	
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Short[] getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(Short[] categoryIds) {
		this.categoryIds = categoryIds;
	}
	public Long[] getAlbumIds() {
		return albumIds;
	}
	public void setAlbumIds(Long[] albumIds) {
		this.albumIds = albumIds;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
}
