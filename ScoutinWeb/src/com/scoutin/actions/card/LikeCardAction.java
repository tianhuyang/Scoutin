package com.scoutin.actions.card;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scoutin.entities.Account;
import com.scoutin.exception.ScoutinError;
import com.scoutin.utilities.CommonUtils;
import com.scoutin.utilities.JSONUtils;
import com.scoutin.vos.card.LikeCardVO;

public class LikeCardAction extends ActionSupport implements ServletRequestAware, ModelDriven<LikeCardVO>
{
	private static final long serialVersionUID = -8744020611661952002L;
	private HttpServletRequest request;
	private LikeCardVO likeCardVO;
	private Map<String, Object> dataMap;
	
	public LikeCardAction()
	{
		dataMap = new HashMap<String, Object>();
		likeCardVO = new LikeCardVO();
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}
	
	@Override
	public LikeCardVO getModel() {
		// TODO Auto-generated method stub
		return likeCardVO;
	}

	public void validate()
	{
		super.validate();
		if(this.hasFieldErrors()){
			JSONUtils.putStatus(dataMap, ScoutinError.Account_Signup_Input_Status, ScoutinError.Account_Signup_Input_Message);
			dataMap.put("fieldErrors", this.getFieldErrors());
		}
	}
	
	public String execute() throws Exception{
		Map<String,Object> properties = new TreeMap<String,Object>();
		CommonUtils.describe(properties, likeCardVO);
		Account account = (Account)request.getSession(true).getAttribute("user");
		properties.put("accountId",account.getAccountId());
		
		return SUCCESS;
	}
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}


}
