package com.scoutin.logic;

import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.exception.*;
import com.scoutin.utilities.EJBUtils;

public class AccountService {

	/*
	 * @param account:(Account) must be not null, have password and (have email or phone)
	 * @return Account, Account.Profile, and Account.Accountstat if successful otherwise throws ScoutinException 
	 */
	public static Account signup(Account account)
			throws ScoutinException {	
		
		if (account == null){
			throw new IllegalArgumentException("null account");
		}
		String email = account.getEmail();
		String password = account.getPassword();

		if ((email == null || email.length() == 0)
				|| (password == null || password.length() == 0)) {
			throw new IllegalArgumentException("Illegal arguments in signup");
		}
	
		try {
			account = EJBUtils.accountBeanRemote.signup(account);
		} catch (Throwable re) {
			account = null;
		}

		if (account == null) {
			throw new ScoutinException(
					ScoutinException.Account_Signup_Failure_Status,
					ScoutinException.Account_Signup_Failure_Message);
		}
		return account;
	}

	/*
	 * @param args:(String[]) must have two valid elements
	 * @param type:(int) must be a valid AuthenticateType
	 * @return Account, Account.Profile, and Account.Accountstat if successful otherwise throws ScoutinException 
	 */
	public static Account authenticate(String[] args, int type)
			throws ScoutinException {
		if (args == null){
			throw new IllegalArgumentException("null args");
		}
		Account account = null;
		try {
			account = EJBUtils.authenticateBeanRemote.authenticate(args, type);
		} catch (Throwable re) {
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
	 * @param accountId:(Integer) must be existent and authenticated
	 * @param album:(Album) must be not null
	 * @return Album if successful otherwise throws ScoutinException 
	 */
	public static Album createAlbum(Integer accountId, Album album)
			throws ScoutinException {
		if (accountId == null || album == null){
			throw new IllegalArgumentException("null arguments");
		}	
		try {
			album = EJBUtils.accountBeanRemote.createAlbum(accountId, album);
		} catch (Throwable re) {
			album = null;
		}
		if (album == null) {
			throw new ScoutinException(
					ScoutinException.Album_CreateAlbum_Failure_Status,
					ScoutinException.Album_CreateAlbum_Failure_Message);
		}
		return album;
	}
}
