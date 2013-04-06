package com.scoutin.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import test.TestRemote;

import com.scoutin.utilities.EJBUtils;

public class SignInAction extends Action {
	
	public SignInAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		//TestRemote tr = (TestRemote)EJBUtils.obtainBean("ScoutinApplication/Scoutin/Test!test.TestRemote");
        ActionErrors errors = (ActionErrors)request.getAttribute("actionErrors");
        errors.add("password",new ActionMessage("error_signin_incorrect","Your email or password is incorrect"));
		return mapping.findForward("input");

	}
}
