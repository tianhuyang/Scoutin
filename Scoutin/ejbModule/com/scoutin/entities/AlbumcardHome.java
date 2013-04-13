package com.scoutin.entities;

// Generated Apr 12, 2013 8:46:14 AM by Hibernate Tools 4.0.0

import com.scoutin.utilities.DaoUtils;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Albumcard.
 * @see com.scoutin.entities.Albumcard
 * @author Hibernate Tools
 */
public class AlbumcardHome {

	private static final Log log = LogFactory.getLog(AlbumcardHome.class);

	public void persist(Albumcard transientInstance) {
		log.debug("persisting Albumcard instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().persist(
					transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void save(Albumcard transientInstance) {
		log.debug("saving Albumcard instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().save(transientInstance);
			log.debug("saving successful");
		} catch (RuntimeException re) {
			log.error("saving failed", re);
			throw re;
		}
	}

	public void attachDirty(Albumcard instance) {
		log.debug("attaching dirty Albumcard instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Albumcard instance) {
		log.debug("attaching clean Albumcard instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Albumcard persistentInstance) {
		log.debug("deleting Albumcard instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().delete(
					persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void evict(Albumcard persistentInstance) {
		log.debug("evicting Albumcard instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().evict(
					persistentInstance);
			log.debug("evicting successful");
		} catch (RuntimeException re) {
			log.error("evicting failed", re);
			throw re;
		}
	}

	public Albumcard merge(Albumcard detachedInstance) {
		log.debug("merging Albumcard instance");
		try {
			Albumcard result = (Albumcard) DaoUtils.sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Albumcard findById(com.scoutin.entities.AlbumcardId id) {
		log.debug("getting Albumcard instance with id: " + id);
		try {
			Albumcard instance = (Albumcard) DaoUtils.sessionFactory
					.getCurrentSession().get("com.scoutin.entities.Albumcard",
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

	public Albumcard load(com.scoutin.entities.AlbumcardId id) {
		log.debug("loading Albumcard instance with id: " + id);
		try {
			Albumcard instance = (Albumcard) DaoUtils.sessionFactory
					.getCurrentSession().load("com.scoutin.entities.Albumcard",
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

	public List findByExample(Albumcard instance) {
		log.debug("finding Albumcard instance by example");
		try {
			List results = DaoUtils.sessionFactory.getCurrentSession()
					.createCriteria("com.scoutin.entities.Albumcard")
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
