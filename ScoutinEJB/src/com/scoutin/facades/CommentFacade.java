package com.scoutin.facades;

import com.scoutin.entities.Comment;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Comment.
 * 
 * @see com.scoutin.entities.Comment
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class CommentFacade {
	// property constants
	public static final String CONTENT = "content";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Comment entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param comment
	 *            Comment entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Comment comment) {
		LogUtil.log("saving Comment instance", Level.INFO, null);
		try {
			entityManager.persist(comment);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Comment entity.
	 * 
	 * @param comment
	 *            Comment entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Comment comment) {
		LogUtil.log("deleting Comment instance", Level.INFO, null);
		try {
			comment = entityManager.getReference(Comment.class,
					comment.getCommentId());
			entityManager.remove(comment);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Comment entity and return it or a copy of it
	 * to the sender. A copy of the Comment entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param comment
	 *            Comment entity to update
	 * @return Comment the persisted Comment entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Comment update(Comment comment) {
		LogUtil.log("updating Comment instance", Level.INFO, null);
		try {
			Comment result = entityManager.merge(comment);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Comment findById(Long commentId) {
		LogUtil.log("finding Comment instance with id: " + commentId,
				Level.INFO, null);
		try {
			Comment instance = entityManager.find(Comment.class, commentId);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Comment getReference(Long commentId) {
		LogUtil.log("getReferencing Comment instance with id: " + commentId,
				Level.INFO, null);
		try {
			Comment instance = entityManager.getReference(Comment.class,
					commentId);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Comment comment) {
		LogUtil.log("detaching Comment instance", Level.INFO, null);
		try {
			entityManager.detach(comment);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(Comment comment) {
		LogUtil.log("refreshing Comment instance", Level.INFO, null);
		try {
			entityManager.refresh(comment);
			LogUtil.log("refresh successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("refresh failed", Level.SEVERE, re);
			throw re;
		}
	}

	/*
	 * for persistent instance, remove directly
	 * 
	 * @see delete
	 */

	public void remove(Comment comment) {
		LogUtil.log("removing Comment instance", Level.INFO, null);
		try {
			entityManager.remove(comment);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flush Comment instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByCommentIdJPQL = "delete from Comment a where a.commentId in (?1)";

	public void removeByCommentId(Long commentId) {
		LogUtil.log("removeByCommentId", Level.INFO, null);
		try {
			Query query = entityManager.createQuery(removeByCommentIdJPQL);
			query.setParameter(1, commentId);
			query.executeUpdate();
			LogUtil.log("removeByCommentId successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("removeByCommentId failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String cardIdJPQL = "select a.card.cardId from Comment a where a.commentId = :commentId";

	public java.lang.Long getCardId(java.lang.Long commentId) {
		LogUtil.log("getAccountIdId with commentId" + commentId, Level.INFO,
				null);
		java.lang.Long cardId;
		try {
			Query query = entityManager.createQuery(cardIdJPQL);
			query.setParameter("commentId", commentId);
			cardId = (java.lang.Long) query.getSingleResult();
			LogUtil.log("getAccountIdId successful", Level.INFO, null);
			return cardId;
		} catch (RuntimeException re) {
			LogUtil.log("getAccountIdId failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String accountIdJPQL = "select a.account.accountId from Comment a where a.commentId = :commentId";

	public java.lang.Integer getAccountId(java.lang.Long commentId) {
		LogUtil.log("getAccountIdId with commentId" + commentId, Level.INFO,
				null);
		java.lang.Integer accountId;
		try {
			Query query = entityManager.createQuery(accountIdJPQL);
			query.setParameter("commentId", commentId);
			accountId = (java.lang.Integer) query.getSingleResult();
			LogUtil.log("getAccountIdId successful", Level.INFO, null);
			return accountId;
		} catch (RuntimeException re) {
			LogUtil.log("getAccountIdId failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Comment entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Comment property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Comment> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Comment instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Comment model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Comment> findByContent(Object content,
			int... rowStartIdxAndCount) {
		return findByProperty(CONTENT, content, rowStartIdxAndCount);
	}

	/**
	 * Find all Comment entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Comment> all Comment entities
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Comment instances", Level.INFO, null);
		try {
			final String queryString = "select model from Comment model";
			Query query = entityManager.createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}