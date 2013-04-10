package com.scoutin.daos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.scoutin.entities.Account;
import com.scoutin.homes.AccountHome;
import com.scoutin.utilities.DAOUtils;

public class AccountDao extends AccountHome {

	private static final Log log = LogFactory.getLog(AccountHome.class);
	private final String emailAuthSql = "from Account where email = ? and password = ?";
	
	public Account authenticateWithEmail(String email, String password) {
		log.debug("authenticate with email");
		Account account = null;
		try {
			Query query = DAOUtils.sessionFactory.getCurrentSession().createQuery(emailAuthSql);
			query.setString(0, email);
			query.setString(1, password);
			account = (Account)query.uniqueResult();
			log.debug("authenticate successful");
		} catch (RuntimeException re) {
			log.error("authenticate failed", re);
			throw re;
		}
		return account;
	}
}
