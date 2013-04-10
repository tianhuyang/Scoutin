package com.scoutin.homes;

// Generated Apr 9, 2013 10:38:05 PM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.scoutin.entities.Follower;

/**
 * Home object for domain model class Follower.
 * @see com.scoutin.entities.Follower
 * @author Hibernate Tools
 */
public class FollowerHome {

	private static final Log log = LogFactory.getLog(FollowerHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Follower transientInstance) {
		log.debug("persisting Follower instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Follower instance) {
		log.debug("attaching dirty Follower instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Follower instance) {
		log.debug("attaching clean Follower instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Follower persistentInstance) {
		log.debug("deleting Follower instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Follower merge(Follower detachedInstance) {
		log.debug("merging Follower instance");
		try {
			Follower result = (Follower) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
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
			Follower instance = (Follower) sessionFactory.getCurrentSession()
					.get("com.scoutin.homes.Follower", id);
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

	public List findByExample(Follower instance) {
		log.debug("finding Follower instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.scoutin.homes.Follower")
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
