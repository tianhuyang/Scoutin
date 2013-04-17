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
 * Home object for domain model class Notification.
 * @see com.scoutin.entities.Notification
 * @author Hibernate Tools
 */
public class NotificationHome {

	private static final Log log = LogFactory.getLog(NotificationHome.class);
	private final String notificationIdsExistHql = "select count(className) from Notification className where className.notificationId in :notificationIds";

	public void persist(Notification transientInstance) {
		log.debug("persisting Notification instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().persist(
					transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void save(Notification transientInstance) {
		log.debug("saving Notification instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().save(transientInstance);
			log.debug("saving successful");
		} catch (RuntimeException re) {
			log.error("saving failed", re);
			throw re;
		}
	}

	public void attachDirty(Notification instance) {
		log.debug("attaching dirty Notification instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Notification instance) {
		log.debug("attaching clean Notification instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Notification persistentInstance) {
		log.debug("deleting Notification instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().delete(
					persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void evict(Notification persistentInstance) {
		log.debug("evicting Notification instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().evict(
					persistentInstance);
			log.debug("evicting successful");
		} catch (RuntimeException re) {
			log.error("evicting failed", re);
			throw re;
		}
	}

	public Notification merge(Notification detachedInstance) {
		log.debug("merging Notification instance");
		try {
			Notification result = (Notification) DaoUtils.sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Notification findById(java.lang.Long id) {
		log.debug("getting Notification instance with id: " + id);
		try {
			Notification instance = (Notification) DaoUtils.sessionFactory
					.getCurrentSession().get(
							"com.scoutin.entities.Notification", id);
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

	public Notification load(java.lang.Long id) {
		log.debug("loading Notification instance with id: " + id);
		try {
			Notification instance = (Notification) DaoUtils.sessionFactory
					.getCurrentSession().load(
							"com.scoutin.entities.Notification", id);
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

	public boolean hasAll(java.lang.Long[] notificationIds) {
		log.debug("Notification hasAll");
		boolean hasAll = false;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(notificationIdsExistHql);
			query.setParameterList("notificationIds", notificationIds);
			Long count = (Long) query.iterate().next();
			hasAll = count == notificationIds.length;
			log.debug("hasAll successful");
		} catch (RuntimeException re) {
			log.error("hasAll failed", re);
			throw re;
		}
		return hasAll;
	}

	public void getAndRemoveProxies(Notification notification,
			Set<String> getFields) {
		this.evict(notification);
	}

	public List findByExample(Notification instance) {
		log.debug("finding Notification instance by example");
		try {
			List results = DaoUtils.sessionFactory.getCurrentSession()
					.createCriteria("com.scoutin.entities.Notification")
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
