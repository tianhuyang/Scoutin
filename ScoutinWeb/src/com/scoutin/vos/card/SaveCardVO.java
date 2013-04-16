package com.scoutin.vos.card;

public class SaveCardVO {
	
	public String title;
	public String description;
	public int rating;
	public String tag;
	public float latitude;
	public float longitude;
	public String address;
	public String url;
	public short[] categoryIds;
	public long[] albumIds;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public short[] getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(short[] categoryIds) {
		this.categoryIds = categoryIds;
	}
	public long[] getAlbumIds() {
		return albumIds;
	}
	public void setAlbumIds(long[] albumIds) {
		this.albumIds = albumIds;
	}
}
