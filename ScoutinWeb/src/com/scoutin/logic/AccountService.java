package com.scoutin.logic;

import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.exception.*;
import com.scoutin.utilities.EJBUtils;

public class AccountService {

	/*
	 * @param account:(Account) must be not null, have password and (have email or phone)
	 * @return Account, Account.Profile, and Account.Accountstat if successful
	 * @throws ScoutinException if failed 
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
			throw new ScoutinException(
					ScoutinException.Account_Signup_Failure_Status,
					ScoutinException.Account_Signup_Failure_Message);
		}
		return account;
	}

	/*
	 * @param args:(String[]) must have two valid elements
	 * @param type:(int) must be a valid AuthenticateType
	 * @return Account, Account.Profile, and Account.Accountstat if successful
	 * @throws ScoutinException if failed 
	 */
	public static Account authenticate(String[] args, int type)
			throws ScoutinException {
		if (args == null){
			throw new IllegalArgumentException("Illegal arguments in authenticate");
		}
		Account account = null;
		try {
			account = EJBUtils.authenticateBeanRemote.authenticate(args, type);
		} catch (Throwable re) {
			throw new ScoutinException(
					ScoutinException.Account_Signin_Failure_Status,
					ScoutinException.Account_Signin_Failure_Message);
		}
		return account;
	}
	
	/*
	 * @param followingAccountId:(Integer) must be existent
	 * @param followedAlbumId:(Integer) must be existent
	 * @return whether the card is followed or not followed
     * @throws ScoutinException if failed 
	 */
	public static boolean followAccount(Integer followingAccountId, Integer followedAccountId) throws ScoutinException{
		if (followingAccountId == null || followedAccountId == null){
			throw new IllegalArgumentException("Illegal arguments in followAccount");
		}
		try {
			return EJBUtils.accountBeanRemote.followAccount(followingAccountId, followedAccountId);
		} catch (Throwable re) {
			throw new ScoutinException(
					ScoutinException.Account_FollowAccount_Failed_Status,
					ScoutinException.Account_FollowAccount_Failed_Message);
		}		
	}

}
