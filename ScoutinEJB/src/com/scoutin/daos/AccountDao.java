package com.scoutin.daos;

import java.util.logging.Level;


import javax.ejb.Singleton;
import javax.persistence.Query;

import com.scoutin.entities.Account;
import com.scoutin.facades.AccountFacade;
import com.scoutin.facades.LogUtil;
@Singleton
public class AccountDao extends AccountFacade {

	private final String emailAuthJPQL = "select account from Account account where account.email = ?1 and account.password = ?2";
	
	public Account authenticateWithEmail(String email, String password) {
		LogUtil.log("authenticate with email", Level.INFO, null);
		Account account = null;
		try {
			Query query = entityManager.createQuery(emailAuthJPQL);
			query.setParameter(1, email);
			query.setParameter(2, password);
			account = (Account)query.getSingleResult();
			LogUtil.log("authenticate successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("authenticate failed", Level.SEVERE, re);
			throw re;
		}
		return account;
	}
	
	
}
