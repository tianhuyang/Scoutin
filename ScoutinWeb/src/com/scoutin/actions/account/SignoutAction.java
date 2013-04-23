package com.scoutin.actions.account;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class SignoutAction extends ActionSupport implements SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5158348411353757676L;
	private Map<String,Object> session;
	
	public SignoutAction()
	{
		
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}
	
	public String execute() throws Exception{
		session.remove("user");
		return SUCCESS;
	}
}
