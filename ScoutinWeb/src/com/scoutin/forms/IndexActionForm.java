package com.scoutin.forms;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class IndexActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();
        if(userName == null || userName.length()<3)
        	errors.add("userName",new ActionMessage("error.login.userName",userName));
        
        if(password == null || password.length()<3)
            errors.add("userPwd",new ActionMessage("error.login.password",password));
        
        request.setAttribute("Strut_Error", errors); 
        
        Iterator<ActionMessage> it = errors.get();
        while(it.hasNext())
        	System.out.println(userName);
        
        return errors;
    }
	
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public String getUserPwd() {
        return password;
    }

    public void setUserPwd(String userPwd) {
        this.password = userPwd;
    }
}
