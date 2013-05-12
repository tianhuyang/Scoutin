package com.scoutin.logic;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.scoutin.application.interfaces.AuthenticateBeanRemote;
import com.scoutin.entities.Account;

/**
 * Session Bean implementation class AuthenticateBean
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.NEVER)
public class AuthenticateBean implements AuthenticateBeanRemote {

	/**
	 * Default constructor.
	 */
	public AuthenticateBean() {
		// TODO Auto-generated constructor stub
	}
	@EJB private AuthenticateService authenticateService;

	/*
	 * @see com.scoutin.logic.AuthenticateBeanRemote#authenticate(String[] args, int type)
	 */
	@Override
	public Account authenticate(String[] args, int type) {
		return authenticateService.authenticate(args, type);
	}

}
