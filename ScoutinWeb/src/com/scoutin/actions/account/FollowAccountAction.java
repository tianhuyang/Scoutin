package com.scoutin.actions.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.scoutin.entities.Account;
import com.scoutin.exception.ScoutinError;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.AccountService;
import com.scoutin.utilities.JSONUtils;

public class FollowAccountAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 7180127944308802403L;
	private Integer followedAccountId;
	private HttpServletRequest request;
	private Map<String,Object> dataMap;
	public FollowAccountAction(){
		this.dataMap = new HashMap<String,Object>();
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
	public void validate()
	{
		super.validate();
		if(this.hasFieldErrors()){
			JSONUtils.putStatus(dataMap, ScoutinError.Account_FollowAccount_Input_Status, ScoutinError.Account_FollowAccount_Input_Message);
			dataMap.put("fieldErrors", this.getFieldErrors());
		}
	}
	
	public String execute() throws Exception{
		boolean succeed = true;
		try{
			Account account = (Account)request.getSession(true).getAttribute("user");
			boolean isfollowed = AccountService.followAccount(account.getAccountId(), followedAccountId);
			dataMap.put("isfollowed", isfollowed);
		}catch(ScoutinException e){
			succeed = false;
			String localizedMessage = getText(e.getMessage(),e.getMessage());
			JSONUtils.putStatus(dataMap, e.getStatus(), localizedMessage);
		}
		
		if(succeed)
			JSONUtils.putOKStatus(dataMap);
		
		return SUCCESS;
	}

	public Integer getFollowedAccountId() {
		return followedAccountId;
	}

	public void setFollowedAccountId(Integer followedAccountId) {
		this.followedAccountId = followedAccountId;
	}

	public Map<String,Object> getDataMap() {
		return dataMap;
	}

}
