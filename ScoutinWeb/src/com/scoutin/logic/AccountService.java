package com.scoutin.logic;

import com.scoutin.entities.Account;
import com.scoutin.exception.*;
import com.scoutin.utilities.EJBUtils;

public class AccountService {

	public static String accountPath = "ScoutinApplication/Scoutin/AccountBean!" + AccountBeanRemote.class.getName();
	public static String authenticatePath = "ScoutinApplication/Scoutin/AuthenticateBean!" + AuthenticateBeanRemote.class.getName();
	public static final int Signup_Failure = 1;
	public static Account signup(String[]args, int type) throws ScoutinException
	{
		Account account= new Account();
		account.setEmail(args[0]);
		account.setPassword(args[1]);
		account.setFirstname(args[2]);
		account.setLastname(args[3]);
		AccountBeanRemote abr = (AccountBeanRemote)EJBUtils.obtainBean(accountPath);		
		try{
			account = abr.signup(account);
		}catch(RuntimeException re){
			account = null;
		}
		if(account == null){
			throw new ScoutinException(ScoutinException.Account_Signup_Failure_Status,ScoutinException.Account_Signup_Failure_Message);
		}
		return account;
	}
	public static Account authenticate(String[]args, int type) throws ScoutinException
	{
		Account account = null;
		AuthenticateBeanRemote abr = (AuthenticateBeanRemote)EJBUtils.obtainBean(authenticatePath);
		try{
		    account = abr.authenticate(args, type);
		}catch(RuntimeException re){
		}
		if(account == null){
			throw new ScoutinException(ScoutinException.Account_Signin_Failure_Status,ScoutinException.Account_Signin_Failure_Message);
		}
		return account;
	}
}
