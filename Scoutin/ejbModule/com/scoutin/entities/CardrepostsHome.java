package com.scoutin.entities;

// Generated Apr 15, 2013 10:22:39 PM by Hibernate Tools 4.0.0

import com.scoutin.utilities.DaoUtils;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Cardreposts.
 * @see com.scoutin.entities.Cardreposts
 * @author Hibernate Tools
 */
public class CardrepostsHome {

	private static final Log log = LogFactory.getLog(CardrepostsHome.class);
	private final String idsExistHql = "select count(className) from Cardreposts className where className.id in :ids";

	public void persist(Cardreposts transientInstance) {
		log.debug("persisting Cardreposts instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().persist(
					transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void save(Cardreposts transientInstance) {
		log.debug("saving Cardreposts instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().save(transientInstance);
			log.debug("saving successful");
		} catch (RuntimeException re) {
			log.error("saving failed", re);
			throw re;
		}
	}

	public void attachDirty(Cardreposts instance) {
		log.debug("attaching dirty Cardreposts instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Cardreposts instance) {
		log.debug("attaching clean Cardreposts instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Cardreposts persistentInstance) {
		log.debug("deleting Cardreposts instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().delete(
					persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void evict(Cardreposts persistentInstance) {
		log.debug("evicting Cardreposts instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().evict(
					persistentInstance);
			log.debug("evicting successful");
		} catch (RuntimeException re) {
			log.error("evicting failed", re);
			throw re;
		}
	}

	public Cardreposts merge(Cardreposts detachedInstance) {
		log.debug("merging Cardreposts instance");
		try {
			Cardreposts result = (Cardreposts) DaoUtils.sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Cardreposts findById(com.scoutin.entities.CardrepostsId id) {
		log.debug("getting Cardreposts instance with id: " + id);
		try {
			Cardreposts instance = (Cardreposts) DaoUtils.sessionFactory
					.getCurrentSession().get(
							"com.scoutin.entities.Cardreposts", id);
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

	public Cardreposts load(com.scoutin.entities.CardrepostsId id) {
		log.debug("loading Cardreposts instance with id: " + id);
		try {
			Cardreposts instance = (Cardreposts) DaoUtils.sessionFactory
					.getCurrentSession().load(
							"com.scoutin.entities.Cardreposts", id);
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

	public boolean hasAll(com.scoutin.entities.CardrepostsId[] ids) {
		log.debug("Cardreposts hasAll");
		boolean hasAll = false;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(idsExistHql);
			query.setParameterList("ids", ids);
			Long count = (Long) query.iterate().next();
			hasAll = count == ids.length;
			log.debug("hasAll successful");
		} catch (RuntimeException re) {
			log.error("hasAll failed", re);
			throw re;
		}
		return hasAll;
	}

	public List findByExample(Cardreposts instance) {
		log.debug("finding Cardreposts instance by example");
		try {
			List results = DaoUtils.sessionFactory.getCurrentSession()
					.createCriteria("com.scoutin.entities.Cardreposts")
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
