package com.scoutin.actions.account;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.scoutin.entities.Account;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.AccountService;
import com.scoutin.utilities.JSONUtils;

public class SigninAction extends ActionSupport {

	private static final long serialVersionUID = 8653684360644771752L;
	private final static String COMPLETION="completion";

	private String email;
	private String phone;
	private String password;
	private int authType;
	
	
	
	public SigninAction() {
		// TODO Auto-generated constructor stub
	}
	
	public String emailSignin() throws Exception{
		return COMPLETION;
	}
	
	public String execute() throws Exception{
		String[] args={email,password};
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			Account account = AccountService.signup(args, authType);
			request.getSession(true).setAttribute("user", account);
		} catch (ScoutinException e) {
			// TODO Auto-generated catch block
			String localizedMessage = getText(e.getMessage(),e.getMessage());
			this.addActionError(JSONUtils.statusToJSONString(e.getStatus(), localizedMessage));
		}
		return COMPLETION;
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

}
