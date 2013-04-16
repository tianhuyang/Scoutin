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
 * Home object for domain model class Comment.
 * @see com.scoutin.entities.Comment
 * @author Hibernate Tools
 */
public class CommentHome {

	private static final Log log = LogFactory.getLog(CommentHome.class);
	private final String commentIdsExistHql = "select count(className) from Comment className where className.commentId in :commentIds";

	public void persist(Comment transientInstance) {
		log.debug("persisting Comment instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().persist(
					transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void save(Comment transientInstance) {
		log.debug("saving Comment instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().save(transientInstance);
			log.debug("saving successful");
		} catch (RuntimeException re) {
			log.error("saving failed", re);
			throw re;
		}
	}

	public void attachDirty(Comment instance) {
		log.debug("attaching dirty Comment instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Comment instance) {
		log.debug("attaching clean Comment instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Comment persistentInstance) {
		log.debug("deleting Comment instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().delete(
					persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void evict(Comment persistentInstance) {
		log.debug("evicting Comment instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().evict(
					persistentInstance);
			log.debug("evicting successful");
		} catch (RuntimeException re) {
			log.error("evicting failed", re);
			throw re;
		}
	}

	public Comment merge(Comment detachedInstance) {
		log.debug("merging Comment instance");
		try {
			Comment result = (Comment) DaoUtils.sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Comment findById(java.lang.Long id) {
		log.debug("getting Comment instance with id: " + id);
		try {
			Comment instance = (Comment) DaoUtils.sessionFactory
					.getCurrentSession()
					.get("com.scoutin.entities.Comment", id);
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

	public Comment load(java.lang.Long id) {
		log.debug("loading Comment instance with id: " + id);
		try {
			Comment instance = (Comment) DaoUtils.sessionFactory
					.getCurrentSession().load("com.scoutin.entities.Comment",
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

	public boolean hasAll(java.lang.Long[] commentIds) {
		log.debug("Comment hasAll");
		boolean hasAll = false;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(commentIdsExistHql);
			query.setParameterList("commentIds", commentIds);
			Long count = (Long) query.iterate().next();
			hasAll = count == commentIds.length;
			log.debug("hasAll successful");
		} catch (RuntimeException re) {
			log.error("hasAll failed", re);
			throw re;
		}
		return hasAll;
	}

	private final String cardIdHql = "select a.card.cardId from Comment a where a.commentId = :commentId";

	public java.lang.Long getCardIdId(java.lang.Long commentId) {
		log.debug("getCardIdId with commentId" + commentId);
		java.lang.Long cardId;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(cardIdHql);
			query.setParameter("commentId", commentId);
			cardId = (java.lang.Long) query.uniqueResult();
			log.debug("getCardIdId successful");
			return cardId;
		} catch (RuntimeException re) {
			log.error("getCardIdId failed", re);
			throw re;
		}
	}

	private final String accountIdHql = "select a.account.accountId from Comment a where a.commentId = :commentId";

	public java.lang.Integer getAccountIdId(java.lang.Long commentId) {
		log.debug("getAccountIdId with commentId" + commentId);
		java.lang.Integer accountId;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(accountIdHql);
			query.setParameter("commentId", commentId);
			accountId = (java.lang.Integer) query.uniqueResult();
			log.debug("getAccountIdId successful");
			return accountId;
		} catch (RuntimeException re) {
			log.error("getAccountIdId failed", re);
			throw re;
		}
	}

	public List findByExample(Comment instance) {
		log.debug("finding Comment instance by example");
		try {
			List results = DaoUtils.sessionFactory.getCurrentSession()
					.createCriteria("com.scoutin.entities.Comment")
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
