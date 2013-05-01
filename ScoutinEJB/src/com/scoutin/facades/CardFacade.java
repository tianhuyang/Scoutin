package com.scoutin.facades;

import com.scoutin.entities.Card;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Card.
 * 
 * @see com.scoutin.entities.Card
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class CardFacade {
	// property constants
	public static final String VERSION = "version";
	public static final String DESCRIPTION = "description";
	public static final String RATING = "rating";
	public static final String COMMENTS_COUNT = "commentsCount";
	public static final String LIKES_COUNT = "likesCount";
	public static final String TAG = "tag";
	public static final String RATING_COUNT = "ratingCount";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Card entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param card
	 *            Card entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Card card) {
		LogUtil.log("saving Card instance", Level.INFO, null);
		try {
			entityManager.persist(card);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Card entity.
	 * 
	 * @param card
	 *            Card entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Card card) {
		LogUtil.log("deleting Card instance", Level.INFO, null);
		try {
			card = entityManager.getReference(Card.class, card.getCardId());
			entityManager.remove(card);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Card entity and return it or a copy of it to
	 * the sender. A copy of the Card entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity.
	 * 
	 * @param card
	 *            Card entity to update
	 * @return Card the persisted Card entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Card update(Card card) {
		LogUtil.log("updating Card instance", Level.INFO, null);
		try {
			Card result = entityManager.merge(card);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Card findById(Long cardId) {
		LogUtil.log("finding Card instance with id: " + cardId, Level.INFO,
				null);
		try {
			Card instance = entityManager.find(Card.class, cardId);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Card getReference(Long cardId) {
		LogUtil.log("getReferencing Card instance with id: " + cardId,
				Level.INFO, null);
		try {
			Card instance = entityManager.getReference(Card.class, cardId);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Card card) {
		LogUtil.log("detaching Card instance", Level.INFO, null);
		try {
			entityManager.detach(card);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(Card card) {
		LogUtil.log("refreshing Card instance", Level.INFO, null);
		try {
			entityManager.refresh(card);
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

	public void remove(Card card) {
		LogUtil.log("removing Card instance", Level.INFO, null);
		try {
			entityManager.remove(card);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flush Card instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByCardIdJPQL = "delete from Card a where a.cardId in (?1)";

	public void removeByCardId(Long cardId) {
		LogUtil.log("removeByCardId", Level.INFO, null);
		try {
			Query query = entityManager.createQuery(removeByCardIdJPQL);
			query.setParameter(1, cardId);
			query.executeUpdate();
			LogUtil.log("removeByCardId successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("removeByCardId failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String cardbodyIdJPQL = "select a.cardbody.cardbodyId from Card a where a.cardId = :cardId";

	public java.lang.Long getCardbodyId(java.lang.Long cardId) {
		LogUtil.log("getCardbodyIdId with cardId" + cardId, Level.INFO, null);
		java.lang.Long cardbodyId;
		try {
			Query query = entityManager.createQuery(cardbodyIdJPQL);
			query.setParameter("cardId", cardId);
			cardbodyId = (java.lang.Long) query.getSingleResult();
			LogUtil.log("getCardbodyIdId successful", Level.INFO, null);
			return cardbodyId;
		} catch (RuntimeException re) {
			LogUtil.log("getCardbodyIdId failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseCommentsCountJPQL = "update Card a set a.commentsCount = a.commentsCount + :count where a.cardId in (:cardId)";

	public void increaseCommentsCount(java.lang.Long cardId, int count) {
		LogUtil.log("increaseRatingCount with cardId:" + cardId, Level.INFO,
				null);
		try {
			Query query = entityManager.createQuery(increaseCommentsCountJPQL);
			query.setParameter("cardId", cardId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseRatingCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseRatingCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseLikesCountJPQL = "update Card a set a.likesCount = a.likesCount + :count where a.cardId in (:cardId)";

	public void increaseLikesCount(java.lang.Long cardId, int count) {
		LogUtil.log("increaseRatingCount with cardId:" + cardId, Level.INFO,
				null);
		try {
			Query query = entityManager.createQuery(increaseLikesCountJPQL);
			query.setParameter("cardId", cardId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseRatingCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseRatingCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseRatingCountJPQL = "update Card a set a.ratingCount = a.ratingCount + :count where a.cardId in (:cardId)";

	public void increaseRatingCount(java.lang.Long cardId, int count) {
		LogUtil.log("increaseRatingCount with cardId:" + cardId, Level.INFO,
				null);
		try {
			Query query = entityManager.createQuery(increaseRatingCountJPQL);
			query.setParameter("cardId", cardId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseRatingCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseRatingCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Card entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Card property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Card> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Card> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		LogUtil.log("finding Card instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Card model where model."
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

	public List<Card> findByVersion(Object version, int... rowStartIdxAndCount) {
		return findByProperty(VERSION, version, rowStartIdxAndCount);
	}

	public List<Card> findByDescription(Object description,
			int... rowStartIdxAndCount) {
		return findByProperty(DESCRIPTION, description, rowStartIdxAndCount);
	}

	public List<Card> findByRating(Object rating, int... rowStartIdxAndCount) {
		return findByProperty(RATING, rating, rowStartIdxAndCount);
	}

	public List<Card> findByCommentsCount(Object commentsCount,
			int... rowStartIdxAndCount) {
		return findByProperty(COMMENTS_COUNT, commentsCount,
				rowStartIdxAndCount);
	}

	public List<Card> findByLikesCount(Object likesCount,
			int... rowStartIdxAndCount) {
		return findByProperty(LIKES_COUNT, likesCount, rowStartIdxAndCount);
	}

	public List<Card> findByTag(Object tag, int... rowStartIdxAndCount) {
		return findByProperty(TAG, tag, rowStartIdxAndCount);
	}

	public List<Card> findByRatingCount(Object ratingCount,
			int... rowStartIdxAndCount) {
		return findByProperty(RATING_COUNT, ratingCount, rowStartIdxAndCount);
	}

	/**
	 * Find all Card entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Card> all Card entities
	 */
	@SuppressWarnings("unchecked")
	public List<Card> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Card instances", Level.INFO, null);
		try {
			final String queryString = "select model from Card model";
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