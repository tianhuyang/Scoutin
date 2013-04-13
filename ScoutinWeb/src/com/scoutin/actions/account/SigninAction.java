package com.scoutin.actions.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.scoutin.entities.Account;
import com.scoutin.exception.ScoutinError;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.AccountService;
import com.scoutin.logic.AccountConstants;
import com.scoutin.utilities.JSONUtils;

public class SigninAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 8653684360644771752L;

	private String email;
	private String phone;
	private String password;
	private int authType;
	private Map<String, Object> session;
	private String method;
	private Map<String, Object> dataMap;
	
	public SigninAction() {
		// TODO Auto-generated constructor stub
		dataMap = new HashMap<String, Object>();
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		session = arg0;
		this.method = ActionContext.getContext().getName();
	}
	
	public void validate(){			
		super.validate();
		if(this.hasFieldErrors()){
			JSONUtils.putStatus(dataMap, ScoutinError.Account_Signup_Input_Status, ScoutinError.Account_Signup_Input_Message);
			dataMap.put("fieldErrors", this.getFieldErrors());
		}
	}
	
	public String emailSignin() throws Exception{
		signin(email,AccountConstants.AuthenticateTypeEmail);
		return SUCCESS;
	}
	
	private void signin(String domain, int type)
	{
		String[] args = {domain,password};
		boolean succeed = true;
		try
		{
			Account account = AccountService.authenticate(args, type);
			session.put("user", account);
			dataMap.put("user", account);
		}
		catch(ScoutinException e){
			succeed = false;
			String localizedMessage = getText(e.getMessage(),e.getMessage());
			JSONUtils.putStatus(dataMap, e.getStatus(), localizedMessage);
		}
		
		//success
		if(succeed){
			JSONUtils.putOKStatus(dataMap);
		}
	}
	
	public String execute() throws Exception{
		return emailSignin();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String domain) {
		this.email = domain;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAuthType() {
		return authType;
	}

	public void setAuthType(int authType) {
		this.authType = authType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getMethod() {
		return method;
	}
	
	public Map<String, Object> getDataMap() {   
        return dataMap;   
    }   
}
