package com.scoutin.actions.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.scoutin.entities.Account;
import com.scoutin.exception.ScoutinError;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.AccountService;
import com.scoutin.logic.AuthenticateType;
import com.scoutin.utilities.JSONUtils;

public class SignupAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = -3591049023437005414L;

	private String email;
	private String phone;
	private String password;
	private String repassword;
	private String firstname;
	private String lastname;
	private String method;
	private HttpServletRequest request;
	Map<String, Object> dataMap;
	
	public SignupAction() {
		// TODO Auto-generated constructor stub
		dataMap = new HashMap<String, Object>();
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		this.method = ActionContext.getContext().getName();
	}
	
	public void validate(){			
		super.validate();
		if(this.hasFieldErrors()){
			JSONUtils.putStatus(dataMap, ScoutinError.Account_Signup_Input_Status, ScoutinError.Account_Signup_Input_Message);
			dataMap.put("fieldErrors", this.getFieldErrors());
		}
	}
	
	public String emailSignup() throws Exception{
		signup(email, AuthenticateType.AuthenticateTypeEmail);
		return SUCCESS;
	}
	
	public String phoneSignup() throws Exception{
		signup(phone, AuthenticateType.AuthenticateTypePhone);
		return SUCCESS;
	}
	
    private void signup(String domain,int type){
    	String[] args={domain,password,firstname,lastname};
    	boolean succeed = true;
		try {
			Account account = AccountService.signup(args, type);
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
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	public String getMethod() {
		return method;
	}	
	
	public Map<String, Object> getDataMap() {   
        return dataMap;   
    }

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}   

}
