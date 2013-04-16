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
 * Home object for domain model class Message.
 * @see com.scoutin.entities.Message
 * @author Hibernate Tools
 */
public class MessageHome {

	private static final Log log = LogFactory.getLog(MessageHome.class);
	private final String messageIdsExistHql = "select count(className) from Message className where className.messageId in :messageIds";

	public void persist(Message transientInstance) {
		log.debug("persisting Message instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().persist(
					transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void save(Message transientInstance) {
		log.debug("saving Message instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().save(transientInstance);
			log.debug("saving successful");
		} catch (RuntimeException re) {
			log.error("saving failed", re);
			throw re;
		}
	}

	public void attachDirty(Message instance) {
		log.debug("attaching dirty Message instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Message instance) {
		log.debug("attaching clean Message instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Message persistentInstance) {
		log.debug("deleting Message instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().delete(
					persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void evict(Message persistentInstance) {
		log.debug("evicting Message instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().evict(
					persistentInstance);
			log.debug("evicting successful");
		} catch (RuntimeException re) {
			log.error("evicting failed", re);
			throw re;
		}
	}

	public Message merge(Message detachedInstance) {
		log.debug("merging Message instance");
		try {
			Message result = (Message) DaoUtils.sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Message findById(java.lang.Long id) {
		log.debug("getting Message instance with id: " + id);
		try {
			Message instance = (Message) DaoUtils.sessionFactory
					.getCurrentSession()
					.get("com.scoutin.entities.Message", id);
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

	public Message load(java.lang.Long id) {
		log.debug("loading Message instance with id: " + id);
		try {
			Message instance = (Message) DaoUtils.sessionFactory
					.getCurrentSession().load("com.scoutin.entities.Message",
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

	public boolean hasAll(java.lang.Long[] messageIds) {
		log.debug("Message hasAll");
		boolean hasAll = false;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(messageIdsExistHql);
			query.setParameterList("messageIds", messageIds);
			Long count = (Long) query.iterate().next();
			hasAll = count == messageIds.length;
			log.debug("hasAll successful");
		} catch (RuntimeException re) {
			log.error("hasAll failed", re);
			throw re;
		}
		return hasAll;
	}

	public List findByExample(Message instance) {
		log.debug("finding Message instance by example");
		try {
			List results = DaoUtils.sessionFactory.getCurrentSession()
					.createCriteria("com.scoutin.entities.Message")
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
