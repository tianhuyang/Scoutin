package com.scoutin.actions.account;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scoutin.entities.Account;
import com.scoutin.exception.ScoutinError;
import com.scoutin.exception.ScoutinException;
import com.scoutin.interfaces.AccountConstants;
import com.scoutin.logic.AccountService;
import com.scoutin.utilities.CommonUtils;
import com.scoutin.utilities.JSONUtils;
import com.scoutin.vos.account.SignupVO;

public class SignupAction extends ActionSupport implements ServletRequestAware, ModelDriven<SignupVO> {

	private static final long serialVersionUID = -3591049023437005414L;

	private String method;
	private HttpServletRequest request;
	private Map<String, Object> dataMap;
	private SignupVO user;
	
	public SignupAction() {
		// TODO Auto-generated constructor stub
		dataMap = new HashMap<String, Object>();
		user = new SignupVO();
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		this.method = ActionContext.getContext().getName();
	}
	
	@Override
	public SignupVO getModel() {
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
	
	public String emailSignup() throws Exception{
		signup(user.getEmail(), AccountConstants.AuthenticateTypeEmail);
		return SUCCESS;
	}
	
	public String phoneSignup() throws Exception{
		signup(user.getPhone(), AccountConstants.AuthenticateTypePhone);
		return SUCCESS;
	}
	
    private void signup(String domain,int type){
    	Map<String, Object> properties = new TreeMap<String, Object>();
    	CommonUtils.describe(properties, user);
    	
    	boolean succeed = true;
		try {
			Account account = AccountService.signup(properties);
			request.getSession(true).setAttribute("user", account);
			dataMap.put("user", account);
		} catch (ScoutinException e) {
			// TODO Auto-generated catch block
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
		return emailSignup();
	}
    
	public String getMethod() {
		return method;
	}	
	
	public Map<String, Object> getDataMap() {   
        return dataMap;   
    }
}
