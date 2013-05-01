package com.scoutin.logic;

import java.util.Map;

import javax.ejb.*;

import com.scoutin.application.interfaces.AccountBeanRemote;
import com.scoutin.application.exception.ApplicationException;
import com.scoutin.entities.Account;
import com.scoutin.entities.Album;

/**
 * Session Bean implementation class AccountBean
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.NEVER)
public class AccountBean implements AccountBeanRemote {

	@EJB
	private AccountBeanService accountBeanService;

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
			return accountBeanService.signup(account);
		} catch (Throwable t) {
			throw new ApplicationException(t.getMessage());
		}
	}

	/*
	 * @see com.scoutin.logic.AccountBeanRemote#followAccount(Integer followingAccountId,Integer followedAccountId)
	 */
	@Override
	public boolean followAccount(Integer followingAccountId,
			Integer followedAccountId) {
		// TODO Auto-generated method stub
		try {
			return accountBeanService.followAccount(followingAccountId, followedAccountId);
		} catch (Throwable t) {
			throw new ApplicationException(t.getMessage());
		}
	}

}
