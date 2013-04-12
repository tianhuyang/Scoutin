package com.scoutin.entities;

// Generated Apr 11, 2013 5:17:27 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Cardbody.
 * @see com.scoutin.entities.Cardbody
 * @author Hibernate Tools
 */
public class CardbodyHome {

	private static final Log log = LogFactory.getLog(CardbodyHome.class);

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

	public void persist(Cardbody transientInstance) {
		log.debug("persisting Cardbody instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Cardbody instance) {
		log.debug("attaching dirty Cardbody instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Cardbody instance) {
		log.debug("attaching clean Cardbody instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Cardbody persistentInstance) {
		log.debug("deleting Cardbody instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Cardbody merge(Cardbody detachedInstance) {
		log.debug("merging Cardbody instance");
		try {
			Cardbody result = (Cardbody) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Cardbody findById(long id) {
		log.debug("getting Cardbody instance with id: " + id);
		try {
			Cardbody instance = (Cardbody) sessionFactory.getCurrentSession()
					.get("com.scoutin.entities.Cardbody", id);
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

	public List findByExample(Cardbody instance) {
		log.debug("finding Cardbody instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
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
