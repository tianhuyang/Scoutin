package com.scoutin.actions.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scoutin.entities.Account;
import com.scoutin.exception.ScoutinError;
import com.scoutin.exception.ScoutinException;
import com.scoutin.interfaces.AccountConstants;
import com.scoutin.logic.AccountService;
import com.scoutin.utilities.JSONUtils;
import com.scoutin.vos.account.SigninVO;

public class SigninAction extends ActionSupport implements SessionAware, ModelDriven<SigninVO>
{
	private static final long serialVersionUID = 8653684360644771752L;

	private Map<String, Object> session;
	private String method;
	private Map<String, Object> dataMap;
	private SigninVO user; 
	
	public SigninAction() {
		// TODO Auto-generated constructor stub
		user = new SigninVO(); 
		dataMap = new HashMap<String, Object>();
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		session = arg0;
		this.method = ActionContext.getContext().getName();
	}
	
	@Override
	public SigninVO getModel() {
		// TODO Auto-generated method stub
		return user;
	}   
	
	public void validate(){			
		super.validate();
		if(this.hasFieldErrors()){
			JSONUtils.putStatus(dataMap, ScoutinError.Account_Signup_Input_Status, ScoutinError.Account_Signup_Input_Message);
			dataMap.put("fieldErrors", this.getFieldErrors());
		}
	}
	
	public String emailSignin() throws Exception{
		signin(user.getEmail(),AccountConstants.AuthenticateTypeEmail);
		return SUCCESS;
	}
	
	private void signin(String domain, int type)
	{
		String[] args = {domain,user.getPassword()};
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
	
	public String getMethod() {
		return method;
	}
	
	public Map<String, Object> getDataMap() {   
        return dataMap;   
    }
}
