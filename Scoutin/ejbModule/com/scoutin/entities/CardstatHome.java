package com.scoutin.entities;

// Generated Apr 12, 2013 8:46:56 AM by Hibernate Tools 4.0.0

import com.scoutin.utilities.DaoUtils;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Cardstat.
 * @see com.scoutin.entities.Cardstat
 * @author Hibernate Tools
 */
public class CardstatHome {

	private static final Log log = LogFactory.getLog(CardstatHome.class);

	public void persist(Cardstat transientInstance) {
		log.debug("persisting Cardstat instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().persist(
					transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void save(Cardstat transientInstance) {
		log.debug("saving Cardstat instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().save(transientInstance);
			log.debug("saving successful");
		} catch (RuntimeException re) {
			log.error("saving failed", re);
			throw re;
		}
	}

	public void attachDirty(Cardstat instance) {
		log.debug("attaching dirty Cardstat instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Cardstat instance) {
		log.debug("attaching clean Cardstat instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Cardstat persistentInstance) {
		log.debug("deleting Cardstat instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().delete(
					persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void evict(Cardstat persistentInstance) {
		log.debug("evicting Cardstat instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().evict(
					persistentInstance);
			log.debug("evicting successful");
		} catch (RuntimeException re) {
			log.error("evicting failed", re);
			throw re;
		}
	}

	public Cardstat merge(Cardstat detachedInstance) {
		log.debug("merging Cardstat instance");
		try {
			Cardstat result = (Cardstat) DaoUtils.sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Cardstat findById(java.lang.Long id) {
		log.debug("getting Cardstat instance with id: " + id);
		try {
			Cardstat instance = (Cardstat) DaoUtils.sessionFactory
					.getCurrentSession().get("com.scoutin.entities.Cardstat",
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

	public Cardstat load(java.lang.Long id) {
		log.debug("loading Cardstat instance with id: " + id);
		try {
			Cardstat instance = (Cardstat) DaoUtils.sessionFactory
					.getCurrentSession().load("com.scoutin.entities.Cardstat",
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

	public List findByExample(Cardstat instance) {
		log.debug("finding Cardstat instance by example");
		try {
			List results = DaoUtils.sessionFactory.getCurrentSession()
					.createCriteria("com.scoutin.entities.Cardstat")
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
