package com.scoutin.entities;

// Generated Apr 16, 2013 7:33:43 PM by Hibernate Tools 4.0.0

import com.scoutin.utilities.DaoUtils;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Account.
 * @see com.scoutin.entities.Account
 * @author Hibernate Tools
 */
public class AccountHome {

	private static final Log log = LogFactory.getLog(AccountHome.class);
	private final String accountIdsExistHql = "select count(className) from Account className where className.accountId in :accountIds";

	public void persist(Account transientInstance) {
		log.debug("persisting Account instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().persist(
					transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void save(Account transientInstance) {
		log.debug("saving Account instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().save(transientInstance);
			log.debug("saving successful");
		} catch (RuntimeException re) {
			log.error("saving failed", re);
			throw re;
		}
	}

	public void attachDirty(Account instance) {
		log.debug("attaching dirty Account instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Account instance) {
		log.debug("attaching clean Account instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Account persistentInstance) {
		log.debug("deleting Account instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().delete(
					persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void evict(Account persistentInstance) {
		log.debug("evicting Account instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().evict(
					persistentInstance);
			log.debug("evicting successful");
		} catch (RuntimeException re) {
			log.error("evicting failed", re);
			throw re;
		}
	}

	public Account merge(Account detachedInstance) {
		log.debug("merging Account instance");
		try {
			Account result = (Account) DaoUtils.sessionFactory
					.getCurrentSession().merge(detachedInstance);
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
			Account instance = (Account) DaoUtils.sessionFactory
					.getCurrentSession()
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

	public Account load(java.lang.Integer id) {
		log.debug("loading Account instance with id: " + id);
		try {
			Account instance = (Account) DaoUtils.sessionFactory
					.getCurrentSession().load("com.scoutin.entities.Account",
							id);
			if (instance == null) {
				log.debug("load successful, no instance found");
			} else {
				log.debug("load successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("load failed", re);
			throw re;
		}
	}

	public boolean hasAll(java.lang.Integer[] accountIds) {
		log.debug("Account hasAll");
		boolean hasAll = false;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(accountIdsExistHql);
			query.setParameterList("accountIds", accountIds);
			Long count = (Long) query.iterate().next();
			hasAll = count == accountIds.length;
			log.debug("hasAll successful");
		} catch (RuntimeException re) {
			log.error("hasAll failed", re);
			throw re;
		}
		return hasAll;
	}

	public void getAndRemoveProxies(Account account, Set<String> getFields) {
		if (getFields.contains("comments"))
			account.getComments().isEmpty();
		if (getFields.contains("albums"))
			account.getAlbums().isEmpty();
		if (getFields.contains("cardrepostses"))
			account.getCardrepostses().isEmpty();
		if (getFields.contains("accountstat"))
			account.getAccountstat();
		if (getFields.contains("profile"))
			account.getProfile();
		if (getFields.contains("followersForFollowingId"))
			account.getFollowersForFollowingId().isEmpty();
		if (getFields.contains("cards"))
			account.getCards().isEmpty();
		if (getFields.contains("cardbodies"))
			account.getCardbodies().isEmpty();
		if (getFields.contains("followersForFollowedId"))
			account.getFollowersForFollowedId().isEmpty();
		this.evict(account);
		if (!getFields.contains("comments"))
			account.setComments(null);
		if (!getFields.contains("albums"))
			account.setAlbums(null);
		if (!getFields.contains("cardrepostses"))
			account.setCardrepostses(null);
		if (!getFields.contains("accountstat"))
			account.setAccountstat(null);
		if (!getFields.contains("profile"))
			account.setProfile(null);
		if (!getFields.contains("followersForFollowingId"))
			account.setFollowersForFollowingId(null);
		if (!getFields.contains("cards"))
			account.setCards(null);
		if (!getFields.contains("cardbodies"))
			account.setCardbodies(null);
		if (!getFields.contains("followersForFollowedId"))
			account.setFollowersForFollowedId(null);
	}

	public List findByExample(Account instance) {
		log.debug("finding Account instance by example");
		try {
			List results = DaoUtils.sessionFactory.getCurrentSession()
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
