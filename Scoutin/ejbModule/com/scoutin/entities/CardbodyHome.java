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
 * Home object for domain model class Cardbody.
 * @see com.scoutin.entities.Cardbody
 * @author Hibernate Tools
 */
public class CardbodyHome {

	private static final Log log = LogFactory.getLog(CardbodyHome.class);
	private final String cardbodyIdsExistHql = "select count(className) from Cardbody className where className.cardbodyId in :cardbodyIds";

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

	public boolean hasAll(java.lang.Long[] cardbodyIds) {
		log.debug("Cardbody hasAll");
		boolean hasAll = false;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(cardbodyIdsExistHql);
			query.setParameterList("cardbodyIds", cardbodyIds);
			Long count = (Long) query.iterate().next();
			hasAll = count == cardbodyIds.length;
			log.debug("hasAll successful");
		} catch (RuntimeException re) {
			log.error("hasAll failed", re);
			throw re;
		}
		return hasAll;
	}

	private final String cardstatIdHql = "select a.cardstat.cardstatId from Cardbody a where a.cardbodyId = :cardbodyId";

	public java.lang.Long getCardstatIdId(java.lang.Long cardbodyId) {
		log.debug("getCardstatIdId with cardbodyId" + cardbodyId);
		java.lang.Long cardstatId;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(cardstatIdHql);
			query.setParameter("cardbodyId", cardbodyId);
			cardstatId = (java.lang.Long) query.uniqueResult();
			log.debug("getCardstatIdId successful");
			return cardstatId;
		} catch (RuntimeException re) {
			log.error("getCardstatIdId failed", re);
			throw re;
		}
	}

	private final String increaseCommentsCountHql = "update Cardbody a set a.commentsCount = a.commentsCount + :count where a.cardbodyId =:cardbodyId";

	public void increaseCommentsCount(java.lang.Long cardbodyId, int count) {
		log.debug("increaseCommentsCount with cardbodyId:" + cardbodyId);
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(increaseCommentsCountHql);
			query.setParameter("cardbodyId", cardbodyId);
			query.setParameter("count", count);
			query.executeUpdate();
			log.debug("increaseCommentsCount successful");
		} catch (RuntimeException re) {
			log.error("increaseCommentsCount failed", re);
			throw re;
		}
	}

	private final String increaseRepostsCountHql = "update Cardbody a set a.repostsCount = a.repostsCount + :count where a.cardbodyId =:cardbodyId";

	public void increaseRepostsCount(java.lang.Long cardbodyId, int count) {
		log.debug("increaseRepostsCount with cardbodyId:" + cardbodyId);
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(increaseRepostsCountHql);
			query.setParameter("cardbodyId", cardbodyId);
			query.setParameter("count", count);
			query.executeUpdate();
			log.debug("increaseRepostsCount successful");
		} catch (RuntimeException re) {
			log.error("increaseRepostsCount failed", re);
			throw re;
		}
	}

	private final String increaseLikesCountHql = "update Cardbody a set a.likesCount = a.likesCount + :count where a.cardbodyId =:cardbodyId";

	public void increaseLikesCount(java.lang.Long cardbodyId, int count) {
		log.debug("increaseLikesCount with cardbodyId:" + cardbodyId);
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(increaseLikesCountHql);
			query.setParameter("cardbodyId", cardbodyId);
			query.setParameter("count", count);
			query.executeUpdate();
			log.debug("increaseLikesCount successful");
		} catch (RuntimeException re) {
			log.error("increaseLikesCount failed", re);
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
