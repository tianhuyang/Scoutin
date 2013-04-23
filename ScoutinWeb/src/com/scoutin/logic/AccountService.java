package com.scoutin.logic;

import java.util.Map;
import java.util.TreeMap;

import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.exception.*;
import com.scoutin.utilities.EJBUtils;

public class AccountService {

	/*
	 * return Account, Account.Profile, and Account.Accountstat
	 */
	public static Account signup(Map<String, Object> properties)
			throws ScoutinException {	
		
		if (properties == null){
			throw new IllegalArgumentException();
		}
//		String email = (String) properties.get("email");
//		String phone = (String) properties.get("phone");
//		String password = (String) properties.get("password");
//
//		if ((email == null || email.length() == 0)
//				&& (phone == null || phone.length() == 0)
//				|| (password == null || password.length() == 0)) {
//			throw new IllegalArgumentException();
//		}

		Account account = null;		
		try {
			account = EJBUtils.accountBeanRemote.signup(properties);
		} catch (RuntimeException re) {
		}

		if (account == null) {
			throw new ScoutinException(
					ScoutinException.Account_Signup_Failure_Status,
					ScoutinException.Account_Signup_Failure_Message);
		}
		return account;
	}

	/*
	 * return Account, Account.Profile, and Account.Accountstat
	 */
	public static Account authenticate(String[] args, int type)
			throws ScoutinException {
		if (args == null){
			throw new IllegalArgumentException();
		}
		Account account = null;
		try {
			account = EJBUtils.authenticateBeanRemote.authenticate(args, type);
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		if (account == null) {
			throw new ScoutinException(
					ScoutinException.Account_Signin_Failure_Status,
					ScoutinException.Account_Signin_Failure_Message);
		}
		return account;
	}

	/*
	 * return Album
	 */
	public static Album createAlbum(Map<String, Object> properties)
			throws ScoutinException {
		if (properties == null){
			throw new IllegalArgumentException();
		}
		Integer accountId = (Integer)properties.get("accountId");
		if (accountId == null){
			throw new IllegalArgumentException();
		}
		Album album = null;		
		try {
			album = EJBUtils.accountBeanRemote.createAlbum(properties);
		} catch (RuntimeException re) {
		}
		if (album == null) {
			throw new ScoutinException(
					ScoutinException.Account_CreateAlbum_Failure_Status,
					ScoutinException.Account_CreateAlbum_Failure_Message);
		}
		return album;
	}
}
