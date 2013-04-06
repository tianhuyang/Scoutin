package com.scoutin.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
		//TestRemote tr = (TestRemote)EJBUtils.obtainBean("ScoutinApplication/Scoutin/Test!test.TestRemote");
		return mapping.findForward("input");
	}
}
