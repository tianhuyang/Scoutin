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
 * Home object for domain model class Cardstat.
 * @see com.scoutin.entities.Cardstat
 * @author Hibernate Tools
 */
public class CardstatHome {

	private static final Log log = LogFactory.getLog(CardstatHome.class);
	private final String cardstatIdsExistHql = "select count(className) from Cardstat className where className.cardstatId in :cardstatIds";

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

	public boolean hasAll(java.lang.Long[] cardstatIds) {
		log.debug("Cardstat hasAll");
		boolean hasAll = false;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(cardstatIdsExistHql);
			query.setParameterList("cardstatIds", cardstatIds);
			Long count = (Long) query.iterate().next();
			hasAll = count == cardstatIds.length;
			log.debug("hasAll successful");
		} catch (RuntimeException re) {
			log.error("hasAll failed", re);
			throw re;
		}
		return hasAll;
	}

	private final String cardIdHql = "select a.card.cardId from Cardstat a where a.cardstatId = :cardstatId";

	public java.lang.Long getCardIdId(java.lang.Long cardstatId) {
		log.debug("getCardIdId with cardstatId" + cardstatId);
		java.lang.Long cardId;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(cardIdHql);
			query.setParameter("cardstatId", cardstatId);
			cardId = (java.lang.Long) query.uniqueResult();
			log.debug("getCardIdId successful");
			return cardId;
		} catch (RuntimeException re) {
			log.error("getCardIdId failed", re);
			throw re;
		}
	}

	private final String increaseCommentsCountHql = "update Cardstat a set a.commentsCount = a.commentsCount + :count where a.cardstatId =:cardstatId";

	public void increaseCommentsCount(java.lang.Long cardstatId, int count) {
		log.debug("increaseCommentsCount with cardstatId:" + cardstatId);
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(increaseCommentsCountHql);
			query.setParameter("cardstatId", cardstatId);
			query.setParameter("count", count);
			query.executeUpdate();
			log.debug("increaseCommentsCount successful");
		} catch (RuntimeException re) {
			log.error("increaseCommentsCount failed", re);
			throw re;
		}
	}

	private final String increaseRepostsCountHql = "update Cardstat a set a.repostsCount = a.repostsCount + :count where a.cardstatId =:cardstatId";

	public void increaseRepostsCount(java.lang.Long cardstatId, int count) {
		log.debug("increaseRepostsCount with cardstatId:" + cardstatId);
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(increaseRepostsCountHql);
			query.setParameter("cardstatId", cardstatId);
			query.setParameter("count", count);
			query.executeUpdate();
			log.debug("increaseRepostsCount successful");
		} catch (RuntimeException re) {
			log.error("increaseRepostsCount failed", re);
			throw re;
		}
	}

	private final String increaseLikesCountHql = "update Cardstat a set a.likesCount = a.likesCount + :count where a.cardstatId =:cardstatId";

	public void increaseLikesCount(java.lang.Long cardstatId, int count) {
		log.debug("increaseLikesCount with cardstatId:" + cardstatId);
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(increaseLikesCountHql);
			query.setParameter("cardstatId", cardstatId);
			query.setParameter("count", count);
			query.executeUpdate();
			log.debug("increaseLikesCount successful");
		} catch (RuntimeException re) {
			log.error("increaseLikesCount failed", re);
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
