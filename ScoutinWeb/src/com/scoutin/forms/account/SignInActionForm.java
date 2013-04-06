package com.scoutin.forms.account;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class SignInActionForm extends ValidatorForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	
	public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errors = super.validate(mapping, request);
		request.setAttribute("actionErrors", errors);
        return errors;
    }
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
