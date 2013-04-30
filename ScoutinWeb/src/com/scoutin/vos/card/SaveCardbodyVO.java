package com.scoutin.vos.card;

public class SaveCardbodyVO {
	
	public Long cardbodyId;
	public String title;
	public Float latitude;
	public Float longitude;
	public String address;
	public String url;
	public Long version;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public Float getlongitude() {
		return longitude;
	}
	public void setlongitude(Float longitude) {
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
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Long getCardbodyId() {
		return cardbodyId;
	}
	public void setCardbodyId(Long cardbodyId) {
		this.cardbodyId = cardbodyId;
	}
}
