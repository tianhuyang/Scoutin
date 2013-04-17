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
 * Home object for domain model class Accountstat.
 * @see com.scoutin.entities.Accountstat
 * @author Hibernate Tools
 */
public class AccountstatHome {

	private static final Log log = LogFactory.getLog(AccountstatHome.class);
	private final String accountIdsExistHql = "select count(className) from Accountstat className where className.accountId in :accountIds";

	public void persist(Accountstat transientInstance) {
		log.debug("persisting Accountstat instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().persist(
					transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void save(Accountstat transientInstance) {
		log.debug("saving Accountstat instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().save(transientInstance);
			log.debug("saving successful");
		} catch (RuntimeException re) {
			log.error("saving failed", re);
			throw re;
		}
	}

	public void attachDirty(Accountstat instance) {
		log.debug("attaching dirty Accountstat instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Accountstat instance) {
		log.debug("attaching clean Accountstat instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Accountstat persistentInstance) {
		log.debug("deleting Accountstat instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().delete(
					persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void evict(Accountstat persistentInstance) {
		log.debug("evicting Accountstat instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().evict(
					persistentInstance);
			log.debug("evicting successful");
		} catch (RuntimeException re) {
			log.error("evicting failed", re);
			throw re;
		}
	}

	public Accountstat merge(Accountstat detachedInstance) {
		log.debug("merging Accountstat instance");
		try {
			Accountstat result = (Accountstat) DaoUtils.sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Accountstat findById(java.lang.Integer id) {
		log.debug("getting Accountstat instance with id: " + id);
		try {
			Accountstat instance = (Accountstat) DaoUtils.sessionFactory
					.getCurrentSession().get(
							"com.scoutin.entities.Accountstat", id);
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

	public Accountstat load(java.lang.Integer id) {
		log.debug("loading Accountstat instance with id: " + id);
		try {
			Accountstat instance = (Accountstat) DaoUtils.sessionFactory
					.getCurrentSession().load(
							"com.scoutin.entities.Accountstat", id);
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
		log.debug("Accountstat hasAll");
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

	private final String increaseFollowingCountHql = "update Accountstat a set a.followingCount = a.followingCount + :count where a.accountId =:accountId";

	public void increaseFollowingCount(java.lang.Integer accountId, int count) {
		log.debug("increaseFollowingCount with accountId:" + accountId);
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(increaseFollowingCountHql);
			query.setParameter("accountId", accountId);
			query.setParameter("count", count);
			query.executeUpdate();
			log.debug("increaseFollowingCount successful");
		} catch (RuntimeException re) {
			log.error("increaseFollowingCount failed", re);
			throw re;
		}
	}

	private final String increaseFollowersCountHql = "update Accountstat a set a.followersCount = a.followersCount + :count where a.accountId =:accountId";

	public void increaseFollowersCount(java.lang.Integer accountId, int count) {
		log.debug("increaseFollowersCount with accountId:" + accountId);
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(increaseFollowersCountHql);
			query.setParameter("accountId", accountId);
			query.setParameter("count", count);
			query.executeUpdate();
			log.debug("increaseFollowersCount successful");
		} catch (RuntimeException re) {
			log.error("increaseFollowersCount failed", re);
			throw re;
		}
	}

	private final String increaseUnviewRecmdCountHql = "update Accountstat a set a.unviewRecmdCount = a.unviewRecmdCount + :count where a.accountId =:accountId";

	public void increaseUnviewRecmdCount(java.lang.Integer accountId, int count) {
		log.debug("increaseUnviewRecmdCount with accountId:" + accountId);
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(increaseUnviewRecmdCountHql);
			query.setParameter("accountId", accountId);
			query.setParameter("count", count);
			query.executeUpdate();
			log.debug("increaseUnviewRecmdCount successful");
		} catch (RuntimeException re) {
			log.error("increaseUnviewRecmdCount failed", re);
			throw re;
		}
	}

	public void getAndRemoveProxies(Accountstat accountstat,
			Set<String> getFields) {
		if (getFields.contains("account"))
			accountstat.getAccount();
		this.evict(accountstat);
		if (!getFields.contains("account"))
			accountstat.setAccount(null);
	}

	public List findByExample(Accountstat instance) {
		log.debug("finding Accountstat instance by example");
		try {
			List results = DaoUtils.sessionFactory.getCurrentSession()
					.createCriteria("com.scoutin.entities.Accountstat")
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
