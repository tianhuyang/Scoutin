package com.scoutin.entities;

// Generated Apr 15, 2013 7:30:19 AM by Hibernate Tools 4.0.0

import com.scoutin.utilities.DaoUtils;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Album.
 * @see com.scoutin.entities.Album
 * @author Hibernate Tools
 */
public class AlbumHome {

	private static final Log log = LogFactory.getLog(AlbumHome.class);
	private final String albumIdsExistHql = "select count(className) from Album className where className.albumId in :albumIds";

	public void persist(Album transientInstance) {
		log.debug("persisting Album instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().persist(
					transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void save(Album transientInstance) {
		log.debug("saving Album instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().save(transientInstance);
			log.debug("saving successful");
		} catch (RuntimeException re) {
			log.error("saving failed", re);
			throw re;
		}
	}

	public void attachDirty(Album instance) {
		log.debug("attaching dirty Album instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Album instance) {
		log.debug("attaching clean Album instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Album persistentInstance) {
		log.debug("deleting Album instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().delete(
					persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void evict(Album persistentInstance) {
		log.debug("evicting Album instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().evict(
					persistentInstance);
			log.debug("evicting successful");
		} catch (RuntimeException re) {
			log.error("evicting failed", re);
			throw re;
		}
	}

	public Album merge(Album detachedInstance) {
		log.debug("merging Album instance");
		try {
			Album result = (Album) DaoUtils.sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Album findById(java.lang.Long id) {
		log.debug("getting Album instance with id: " + id);
		try {
			Album instance = (Album) DaoUtils.sessionFactory
					.getCurrentSession().get("com.scoutin.entities.Album", id);
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

	public Album load(java.lang.Long id) {
		log.debug("loading Album instance with id: " + id);
		try {
			Album instance = (Album) DaoUtils.sessionFactory
					.getCurrentSession().load("com.scoutin.entities.Album", id);
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

	public boolean hasAll(java.lang.Long[] albumIds) {
		log.debug("Album hasAll");
		boolean hasAll = false;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(albumIdsExistHql);
			query.setParameterList("albumIds", albumIds);
			Long count = (Long) query.iterate().next();
			hasAll = count == albumIds.length;
			log.debug("hasAll successful");
		} catch (RuntimeException re) {
			log.error("hasAll failed", re);
			throw re;
		}
		return hasAll;
	}

	private final String accountIdHql = "select a.account.accountId from Album a where a.albumId = :albumId";

	public java.lang.Integer getAccountIdId(java.lang.Long albumId) {
		log.debug("getAccountIdId with albumId" + albumId);
		java.lang.Integer accountId;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(accountIdHql);
			query.setParameter("albumId", albumId);
			accountId = (java.lang.Integer) query.uniqueResult();
			log.debug("getAccountIdId successful");
			return accountId;
		} catch (RuntimeException re) {
			log.error("getAccountIdId failed", re);
			throw re;
		}
	}

	private final String increaseFollowCountHql = "update Album a set a.followCount = a.followCount + :count where a.albumId =:albumId";

	public void increaseFollowCount(java.lang.Long albumId, int count) {
		log.debug("increaseFollowCount with albumId:" + albumId);
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(increaseFollowCountHql);
			query.setParameter("albumId", albumId);
			query.setParameter("count", count);
			query.executeUpdate();
			log.debug("increaseFollowCount successful");
		} catch (RuntimeException re) {
			log.error("increaseFollowCount failed", re);
			throw re;
		}
	}

	public List findByExample(Album instance) {
		log.debug("finding Album instance by example");
		try {
			List results = DaoUtils.sessionFactory.getCurrentSession()
					.createCriteria("com.scoutin.entities.Album")
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
