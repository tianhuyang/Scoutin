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
 * Home object for domain model class Card.
 * @see com.scoutin.entities.Card
 * @author Hibernate Tools
 */
public class CardHome {

	private static final Log log = LogFactory.getLog(CardHome.class);
	private final String cardIdsExistHql = "select count(className) from Card className where className.cardId in :cardIds";

	public void persist(Card transientInstance) {
		log.debug("persisting Card instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().persist(
					transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void save(Card transientInstance) {
		log.debug("saving Card instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().save(transientInstance);
			log.debug("saving successful");
		} catch (RuntimeException re) {
			log.error("saving failed", re);
			throw re;
		}
	}

	public void attachDirty(Card instance) {
		log.debug("attaching dirty Card instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Card instance) {
		log.debug("attaching clean Card instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Card persistentInstance) {
		log.debug("deleting Card instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().delete(
					persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void evict(Card persistentInstance) {
		log.debug("evicting Card instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().evict(
					persistentInstance);
			log.debug("evicting successful");
		} catch (RuntimeException re) {
			log.error("evicting failed", re);
			throw re;
		}
	}

	public Card merge(Card detachedInstance) {
		log.debug("merging Card instance");
		try {
			Card result = (Card) DaoUtils.sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Card findById(java.lang.Long id) {
		log.debug("getting Card instance with id: " + id);
		try {
			Card instance = (Card) DaoUtils.sessionFactory.getCurrentSession()
					.get("com.scoutin.entities.Card", id);
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

	public Card load(java.lang.Long id) {
		log.debug("loading Card instance with id: " + id);
		try {
			Card instance = (Card) DaoUtils.sessionFactory.getCurrentSession()
					.load("com.scoutin.entities.Card", id);
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

	public boolean hasAll(java.lang.Long[] cardIds) {
		log.debug("Card hasAll");
		boolean hasAll = false;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(cardIdsExistHql);
			query.setParameterList("cardIds", cardIds);
			Long count = (Long) query.iterate().next();
			hasAll = count == cardIds.length;
			log.debug("hasAll successful");
		} catch (RuntimeException re) {
			log.error("hasAll failed", re);
			throw re;
		}
		return hasAll;
	}

	private final String cardbodyIdHql = "select a.cardbody.cardbodyId from Card a where a.cardId = :cardId";

	public java.lang.Long getCardbodyIdId(java.lang.Long cardId) {
		log.debug("getCardbodyIdId with cardId" + cardId);
		java.lang.Long cardbodyId;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(cardbodyIdHql);
			query.setParameter("cardId", cardId);
			cardbodyId = (java.lang.Long) query.uniqueResult();
			log.debug("getCardbodyIdId successful");
			return cardbodyId;
		} catch (RuntimeException re) {
			log.error("getCardbodyIdId failed", re);
			throw re;
		}
	}

	private final String increaseCommentsCountHql = "update Card a set a.commentsCount = a.commentsCount + :count where a.cardId =:cardId";

	public void increaseCommentsCount(java.lang.Long cardId, int count) {
		log.debug("increaseCommentsCount with cardId:" + cardId);
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(increaseCommentsCountHql);
			query.setParameter("cardId", cardId);
			query.setParameter("count", count);
			query.executeUpdate();
			log.debug("increaseCommentsCount successful");
		} catch (RuntimeException re) {
			log.error("increaseCommentsCount failed", re);
			throw re;
		}
	}

	private final String increaseRepostsCountHql = "update Card a set a.repostsCount = a.repostsCount + :count where a.cardId =:cardId";

	public void increaseRepostsCount(java.lang.Long cardId, int count) {
		log.debug("increaseRepostsCount with cardId:" + cardId);
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(increaseRepostsCountHql);
			query.setParameter("cardId", cardId);
			query.setParameter("count", count);
			query.executeUpdate();
			log.debug("increaseRepostsCount successful");
		} catch (RuntimeException re) {
			log.error("increaseRepostsCount failed", re);
			throw re;
		}
	}

	private final String increaseLikesCountHql = "update Card a set a.likesCount = a.likesCount + :count where a.cardId =:cardId";

	public void increaseLikesCount(java.lang.Long cardId, int count) {
		log.debug("increaseLikesCount with cardId:" + cardId);
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(increaseLikesCountHql);
			query.setParameter("cardId", cardId);
			query.setParameter("count", count);
			query.executeUpdate();
			log.debug("increaseLikesCount successful");
		} catch (RuntimeException re) {
			log.error("increaseLikesCount failed", re);
			throw re;
		}
	}

	public List findByExample(Card instance) {
		log.debug("finding Card instance by example");
		try {
			List results = DaoUtils.sessionFactory.getCurrentSession()
					.createCriteria("com.scoutin.entities.Card")
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
