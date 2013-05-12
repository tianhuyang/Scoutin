package com.scoutin.logic;

import javax.ejb.*;
import javax.persistence.EntityExistsException;

import com.scoutin.application.interfaces.AccountBeanRemote;
import com.scoutin.application.exception.ApplicationException;
import com.scoutin.entities.Account;

/**
 * Session Bean implementation class AccountBean
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.NEVER)
public class AccountBean implements AccountBeanRemote {

	@EJB
	private AccountService accountService;

	/**
	 * Default constructor.
	 */
	public AccountBean() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * @see com.scoutin.logic.AccountBeanRemote#signup(Account account)
	 */
	@Override
	public Account signup(Account account) {
		try {
			return accountService.signup(account);
		} catch (Throwable t) {
			throw new ApplicationException(t.getMessage());
		}
	}

	/*
	 * @see com.scoutin.logic.AccountBeanRemote#followAccount(Integer followingAccountId,Integer followedAccountId)
	 */
	@Override
	public void followAccount(Integer followingAccountId,
			Integer followedAccountId, boolean followed) {
		// TODO Auto-generated method stub
		try {
			accountService.followAccount(followingAccountId, followedAccountId,followed);
		} catch (Throwable t) {
			if (t.getCause() instanceof EntityExistsException == false)
				throw new ApplicationException(t.getMessage());
		}
	}

}
