package com.scoutin.daos;

// Generated Apr 4, 2013 10:16:46 PM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import javax.transaction.Transaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.scoutin.entities.Account;
import com.scoutin.utilities.HibernateUtils;

/**
 * Home object for domain model class Account.
 * @see com.scoutin.entities.Account
 * @author Hibernate Tools
 */
public class AccountHome {

	private static final Log log = LogFactory.getLog(AccountHome.class);
	private final String emailAuthSql = "from Account where email = ? and password = ?";

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return HibernateUtils.sessionFactory;
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public Account authenticateWithEmail(String email, String password) {
		log.debug("authenticate with email");
		Account account = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(emailAuthSql);
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
	public void persist(Account transientInstance) {
		log.debug("persisting Account instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Account instance) {
		log.debug("attaching dirty Account instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}

	}

	public void attachClean(Account instance) {
		log.debug("attaching clean Account instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Account persistentInstance) {
		log.debug("deleting Account instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Account merge(Account detachedInstance) {
		log.debug("merging Account instance");
		try {
			Account result = (Account) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Account findById(java.lang.Integer id) {
		log.debug("getting Account instance with id: " + id);
		try {
			Account instance = (Account) sessionFactory.getCurrentSession()
					.get("com.scoutin.entities.Account", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Account instance) {
		log.debug("finding Account instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.scoutin.entities.Account")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
