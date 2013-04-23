package com.scoutin.vos.card;

public class RepostCardVO {
	public String description; 
	public int rating;
	public long cardbodyId;
	public long[] albumIds;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public long getCardbodyId() {
		return cardbodyId;
	}
	public void setCardbodyId(long cardBodyId) {
		this.cardbodyId = cardBodyId;
	}
	public long[] getAlbumIds() {
		return albumIds;
	}
	public void setAlbumIds(long[] albumIds) {
		this.albumIds = albumIds;
	}
	
	
}
