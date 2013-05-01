package com.scoutin.actions.album;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.scoutin.entities.Account;
import com.scoutin.exception.ScoutinError;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.AccountService;
import com.scoutin.logic.AlbumService;
import com.scoutin.utilities.JSONUtils;

public class BlockAlbumAction extends ActionSupport implements ServletRequestAware{


	private static final long serialVersionUID = 7757921348954306566L;
	private HttpServletRequest request;
	private Map<String,Object> dataMap;
	private Long followedAlbumId;
	public BlockAlbumAction(){
		this.dataMap = new HashMap<String,Object>();
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void validate()
	{
		super.validate();
		if(this.hasFieldErrors()){
			JSONUtils.putStatus(dataMap, ScoutinError.Album_BlockAlbum_Input_Status, ScoutinError.Album_BlockAlbum_Input_Message);
			dataMap.put("fieldErrors", this.getFieldErrors());
		}
	}
	
	public String execute() throws Exception{
		boolean succeed = true;
		try{
			Account account = (Account)request.getSession(true).getAttribute("user");
			boolean isBlocked = AlbumService.blockAlbum(account.getAccountId(), followedAlbumId);
			dataMap.put("isBlocked", isBlocked);
		}catch(ScoutinException e){
			succeed = false;
			String localizedMessage = getText(e.getMessage(),e.getMessage());
			JSONUtils.putStatus(dataMap, e.getStatus(), localizedMessage);
		}
		
		if(succeed)
			JSONUtils.putOKStatus(dataMap);
		
		return SUCCESS;
	}

	public Map<String,Object> getDataMap() {
		return dataMap;
	}

	public Long getFollowedAlbumId() {
		return followedAlbumId;
	}

	public void setFollowedAlbumId(Long followedAlbumId) {
		this.followedAlbumId = followedAlbumId;
	}
}
