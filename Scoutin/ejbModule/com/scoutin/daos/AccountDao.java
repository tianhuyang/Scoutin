package com.scoutin.daos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.scoutin.entities.Account;
import com.scoutin.entities.AccountHome;
import com.scoutin.utilities.DaoUtils;

public class AccountDao extends AccountHome {

	private static final Log log = LogFactory.getLog(AccountHome.class);
	private final String emailAuthSql = "from Account where email = ? and password = ?";
	
	public Account authenticateWithEmail(String email, String password) {
		log.debug("authenticate with email");
		Account account = null;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession().createQuery(emailAuthSql);
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
	
	public Account load(int id) {
		log.debug("loading Account instance with id: " + id);
		try {
			Account instance = (Account) DaoUtils.sessionFactory.getCurrentSession()
					.load("com.scoutin.entities.Account", id);	
			log.debug("loading successful, instance found");
			return instance;
		} catch (RuntimeException re) {
			log.error("loading failed", re);
			throw re;
		}
	}
}
