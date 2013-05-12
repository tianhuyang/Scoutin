package com.scoutin.facades;

import com.scoutin.entities.Recommendation;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Recommendation.
 * 
 * @see com.scoutin.entities.Recommendation
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class RecommendationFacade {
	// property constants
	public static final String IS_VIEWED = "isViewed";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Recommendation entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param recommendation
	 *            Recommendation entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Recommendation recommendation) {
		LogUtil.log("saving Recommendation instance", Level.INFO, null);
		try {
			entityManager.persist(recommendation);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Recommendation entity.
	 * 
	 * @param recommendation
	 *            Recommendation entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Recommendation recommendation) {
		LogUtil.log("deleting Recommendation instance", Level.INFO, null);
		try {
			recommendation = entityManager.getReference(Recommendation.class,
					recommendation.getRecommendationId());
			entityManager.remove(recommendation);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Recommendation entity and return it or a copy
	 * of it to the sender. A copy of the Recommendation entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity.
	 * 
	 * @param recommendation
	 *            Recommendation entity to update
	 * @return Recommendation the persisted Recommendation entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Recommendation update(Recommendation recommendation) {
		LogUtil.log("updating Recommendation instance", Level.INFO, null);
		try {
			Recommendation result = entityManager.merge(recommendation);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Recommendation findById(Long recommendationId) {
		LogUtil.log("finding Recommendation instance with id: "
				+ recommendationId, Level.INFO, null);
		try {
			Recommendation instance = entityManager.find(Recommendation.class,
					recommendationId);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Recommendation getReference(Long recommendationId) {
		LogUtil.log("getReferencing Recommendation instance with id: "
				+ recommendationId, Level.INFO, null);
		try {
			Recommendation instance = entityManager.getReference(
					Recommendation.class, recommendationId);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Recommendation recommendation) {
		LogUtil.log("detaching Recommendation instance", Level.INFO, null);
		try {
			entityManager.detach(recommendation);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(Recommendation recommendation) {
		LogUtil.log("refreshing Recommendation instance", Level.INFO, null);
		try {
			entityManager.refresh(recommendation);
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

	public void remove(Recommendation recommendation) {
		LogUtil.log("removing Recommendation instance", Level.INFO, null);
		try {
			entityManager.remove(recommendation);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flushing Recommendation instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void clear() {
		LogUtil.log("clearing Recommendation instance", Level.INFO, null);
		try {
			entityManager.clear();
			LogUtil.log("clear successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("clear failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByRecommendationIdJPQL = "delete from Recommendation a where a.recommendationId in (?1)";

	public int removeByRecommendationId(Long recommendationId) {
		LogUtil.log("removeByRecommendationId", Level.INFO, null);
		int ret = 0;
		try {
			Query query = entityManager
					.createQuery(removeByRecommendationIdJPQL);
			query.setParameter(1, recommendationId);
			ret = query.executeUpdate();
			LogUtil.log("removeByRecommendationId successful", Level.INFO, null);
			return ret;
		} catch (RuntimeException re) {
			LogUtil.log("removeByRecommendationId failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String cardIdJPQL = "select a.card.cardId from Recommendation a where a.recommendationId = :recommendationId";

	public java.lang.Long getCardId(java.lang.Long recommendationId) {
		LogUtil.log("getAccountIdId with recommendationId" + recommendationId,
				Level.INFO, null);
		java.lang.Long cardId;
		try {
			Query query = entityManager.createQuery(cardIdJPQL);
			query.setParameter("recommendationId", recommendationId);
			cardId = (java.lang.Long) query.getSingleResult();
			LogUtil.log("getAccountIdId successful", Level.INFO, null);
			return cardId;
		} catch (RuntimeException re) {
			LogUtil.log("getAccountIdId failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String accountIdJPQL = "select a.account.accountId from Recommendation a where a.recommendationId = :recommendationId";

	public java.lang.Integer getAccountId(java.lang.Long recommendationId) {
		LogUtil.log("getAccountIdId with recommendationId" + recommendationId,
				Level.INFO, null);
		java.lang.Integer accountId;
		try {
			Query query = entityManager.createQuery(accountIdJPQL);
			query.setParameter("recommendationId", recommendationId);
			accountId = (java.lang.Integer) query.getSingleResult();
			LogUtil.log("getAccountIdId successful", Level.INFO, null);
			return accountId;
		} catch (RuntimeException re) {
			LogUtil.log("getAccountIdId failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Recommendation entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Recommendation property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Recommendation> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Recommendation> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Recommendation instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Recommendation model where model."
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

	public List<Recommendation> findByIsViewed(Object isViewed,
			int... rowStartIdxAndCount) {
		return findByProperty(IS_VIEWED, isViewed, rowStartIdxAndCount);
	}

	/**
	 * Find all Recommendation entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Recommendation> all Recommendation entities
	 */
	@SuppressWarnings("unchecked")
	public List<Recommendation> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Recommendation instances", Level.INFO, null);
		try {
			final String queryString = "select model from Recommendation model";
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