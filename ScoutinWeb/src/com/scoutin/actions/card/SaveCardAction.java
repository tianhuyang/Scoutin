package com.scoutin.actions.card;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.scoutin.exception.ScoutinError;
import com.scoutin.utilities.JSONUtils;

public class SaveCardAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3691178891261906745L;
	private HttpServletRequest request;
	private Map<String, Object> dataMap;
	
	private String title;
	private String description;
	private String rating;
	private String tag;
	private float latitude;
	private float longtitude;
	private String address;
	private String url;
	private short[] categoryIds;
	private long[] albumsIds;
	
	public SaveCardAction()
	{
		dataMap = new HashMap<String, Object>();
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}
	
	public void validate()
	{
		super.validate();
		if(this.hasFieldErrors()){
			JSONUtils.putStatus(dataMap, ScoutinError.Account_Signup_Input_Status, ScoutinError.Account_Signup_Input_Message);
			dataMap.put("fieldErrors", this.getFieldErrors());
		}
	}
	
	public String createCard() throws Exception{
		return SUCCESS;
	}
	
	public String editCard() throws Exception{
		return SUCCESS;
	}
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
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
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
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
	public float getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(float longtitude) {
		this.longtitude = longtitude;
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
	public short[] getCategoryId() {
		return categoryIds;
	}
	public void setCategoryId(short[] categoryId) {
		this.categoryIds = categoryId;
	}
	public long[] getAlbumsId() {
		return albumsIds;
	}
	public void setAlbumsId(long[] albumsId) {
		this.albumsIds = albumsId;
	}
}
