package com.scoutin.entities;

// Generated Apr 12, 2013 8:46:56 AM by Hibernate Tools 4.0.0

import com.scoutin.utilities.DaoUtils;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Follower.
 * @see com.scoutin.entities.Follower
 * @author Hibernate Tools
 */
public class FollowerHome {

	private static final Log log = LogFactory.getLog(FollowerHome.class);

	public void persist(Follower transientInstance) {
		log.debug("persisting Follower instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().persist(
					transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void save(Follower transientInstance) {
		log.debug("saving Follower instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().save(transientInstance);
			log.debug("saving successful");
		} catch (RuntimeException re) {
			log.error("saving failed", re);
			throw re;
		}
	}

	public void attachDirty(Follower instance) {
		log.debug("attaching dirty Follower instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Follower instance) {
		log.debug("attaching clean Follower instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Follower persistentInstance) {
		log.debug("deleting Follower instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().delete(
					persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void evict(Follower persistentInstance) {
		log.debug("evicting Follower instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().evict(
					persistentInstance);
			log.debug("evicting successful");
		} catch (RuntimeException re) {
			log.error("evicting failed", re);
			throw re;
		}
	}

	public Follower merge(Follower detachedInstance) {
		log.debug("merging Follower instance");
		try {
			Follower result = (Follower) DaoUtils.sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Follower findById(com.scoutin.entities.FollowerId id) {
		log.debug("getting Follower instance with id: " + id);
		try {
			Follower instance = (Follower) DaoUtils.sessionFactory
					.getCurrentSession().get("com.scoutin.entities.Follower",
							id);
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

	public Follower load(com.scoutin.entities.FollowerId id) {
		log.debug("loading Follower instance with id: " + id);
		try {
			Follower instance = (Follower) DaoUtils.sessionFactory
					.getCurrentSession().load("com.scoutin.entities.Follower",
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

	public List findByExample(Follower instance) {
		log.debug("finding Follower instance by example");
		try {
			List results = DaoUtils.sessionFactory.getCurrentSession()
					.createCriteria("com.scoutin.entities.Follower")
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
