package com.scoutin.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.scoutin.entities.Account;
import com.scoutin.exception.ScoutinException;
import com.scoutin.forms.account.SignUpActionForm;
import com.scoutin.logic.AccountService;
import com.scoutin.utilities.EJBUtils;

import test.TestRemote;

public class SignUpAction extends Action {
	
	public SignUpAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		SignUpActionForm signUpActionForm = (SignUpActionForm)form;
		String[] args={signUpActionForm.getEmail(),signUpActionForm.getPassword()};
		try {
			int id = AccountService.signup(args, Account.AuthenticateTypeEmail);
			request.getSession().setAttribute("user_id", id);
		} catch (ScoutinException e) {
			// TODO Auto-generated catch block
			ActionErrors errors = (ActionErrors)request.getAttribute("actionErrors");
			errors.add("password",new ActionMessage(e.getMessage(),"Sign up failed"));
			e.printStackTrace();
		}
		return mapping.findForward("input");
	}
}
