package com.scoutin.entities;

// Generated Apr 12, 2013 8:46:56 AM by Hibernate Tools 4.0.0

import com.scoutin.utilities.DaoUtils;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Cardbody.
 * @see com.scoutin.entities.Cardbody
 * @author Hibernate Tools
 */
public class CardbodyHome {

	private static final Log log = LogFactory.getLog(CardbodyHome.class);

	public void persist(Cardbody transientInstance) {
		log.debug("persisting Cardbody instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().persist(
					transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void save(Cardbody transientInstance) {
		log.debug("saving Cardbody instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().save(transientInstance);
			log.debug("saving successful");
		} catch (RuntimeException re) {
			log.error("saving failed", re);
			throw re;
		}
	}

	public void attachDirty(Cardbody instance) {
		log.debug("attaching dirty Cardbody instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Cardbody instance) {
		log.debug("attaching clean Cardbody instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Cardbody persistentInstance) {
		log.debug("deleting Cardbody instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().delete(
					persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void evict(Cardbody persistentInstance) {
		log.debug("evicting Cardbody instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().evict(
					persistentInstance);
			log.debug("evicting successful");
		} catch (RuntimeException re) {
			log.error("evicting failed", re);
			throw re;
		}
	}

	public Cardbody merge(Cardbody detachedInstance) {
		log.debug("merging Cardbody instance");
		try {
			Cardbody result = (Cardbody) DaoUtils.sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Cardbody findById(java.lang.Long id) {
		log.debug("getting Cardbody instance with id: " + id);
		try {
			Cardbody instance = (Cardbody) DaoUtils.sessionFactory
					.getCurrentSession().get("com.scoutin.entities.Cardbody",
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

	public Cardbody load(java.lang.Long id) {
		log.debug("loading Cardbody instance with id: " + id);
		try {
			Cardbody instance = (Cardbody) DaoUtils.sessionFactory
					.getCurrentSession().load("com.scoutin.entities.Cardbody",
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

	public List findByExample(Cardbody instance) {
		log.debug("finding Cardbody instance by example");
		try {
			List results = DaoUtils.sessionFactory.getCurrentSession()
					.createCriteria("com.scoutin.entities.Cardbody")
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
