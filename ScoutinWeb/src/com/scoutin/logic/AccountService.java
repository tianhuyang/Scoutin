package com.scoutin.logic;

import com.scoutin.entities.Account;
import com.scoutin.exception.*;
import com.scoutin.utilities.EJBUtils;

public class AccountService {

	public static String accountPath = "ScoutinApplication/Scoutin/AccountBean!" + AccountBeanRemote.class.getName();
	public static String authenticatePath = "ScoutinApplication/Scoutin/AuthenticateBean!" + AuthenticateBeanRemote.class.getName();
	public static final int Signup_Failure = 1;
	public static int signup(String[]args, int type) throws ScoutinException
	{
		int ret = -1 ;
		Account account= new Account();
		account.setEmail(args[0]);
		account.setPassword(args[1]);
		AccountBeanRemote abr = (AccountBeanRemote)EJBUtils.obtainBean(accountPath);		
		try{
		    ret = abr.signup(account);
		}catch(RuntimeException re){
		}
		if(ret == -1){
			throw new ScoutinException(ScoutinException.Account_Signup_Failure_Code,ScoutinException.Account_Signup_Failure_Text);
		}
		return ret;
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
			throw new ScoutinException(ScoutinException.Account_Signin_Failure_Code,ScoutinException.Account_Signin_Failure_Text);
		}
		return account;
	}
}
