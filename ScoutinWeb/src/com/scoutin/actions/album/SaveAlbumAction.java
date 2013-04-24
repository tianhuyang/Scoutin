package com.scoutin.actions.album;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.exception.ScoutinError;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.AccountService;
import com.scoutin.utilities.CommonUtils;
import com.scoutin.utilities.JSONUtils;
import com.scoutin.vos.album.SaveAlbumVO;

public class SaveAlbumAction extends ActionSupport implements ServletRequestAware, ModelDriven<SaveAlbumVO>{

	private static final long serialVersionUID = -1250115319859018494L;
	private HttpServletRequest request;
	private SaveAlbumVO saveAlbumVO;
	private Map<String,Object> dataMap;
	
	public SaveAlbumAction(){
		dataMap = new HashMap<String,Object>();
		saveAlbumVO = new SaveAlbumVO();
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}
	
	public void validate(){
		super.validate();
		if(this.hasFieldErrors()){
			JSONUtils.putStatus(dataMap, ScoutinError.Album_CreateAlbum_Input_Status, ScoutinError.Album_CreateAlbum_Input_Message);
			dataMap.put("fieldErrors", this.getFieldErrors());
		}
	}
	
	public String createAlbum() throws Exception{
		boolean succeed = true;
		Map<String,Object> properties = new TreeMap<String,Object>();
		CommonUtils.describe(properties, saveAlbumVO);
		Account account = (Account) request.getSession(true).getAttribute("user");
		properties.put("accountId", account.getAccountId());
		try{
			Album album = AccountService.createAlbum(properties);
			dataMap.put("album", album);
		}catch(ScoutinException e){
			succeed = false;
			String localizedMessage = getText(e.getMessage(),e.getMessage());
			JSONUtils.putStatus(dataMap, e.getStatus(), localizedMessage);
		}
		
		if(succeed)
			JSONUtils.putOKStatus(dataMap);
		
		return SUCCESS;
	}
	
	public String editAlbum() throws Exception{
		return SUCCESS;
	}
	
	public String execute() throws Exception{
		return createAlbum();
	}

	@Override
	public SaveAlbumVO getModel() {
		// TODO Auto-generated method stub
		return saveAlbumVO;
	}

	public Map<String,Object> getDataMap() {
		return dataMap;
	}
}
