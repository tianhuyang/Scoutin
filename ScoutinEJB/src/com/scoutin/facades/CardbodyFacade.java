package com.scoutin.facades;

import com.scoutin.entities.CardBody;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity CardBody.
 * 
 * @see com.scoutin.entities.CardBody
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class CardBodyFacade {
	// property constants
	public static final String COMMENTS_COUNT = "commentsCount";
	public static final String REPOSTS_COUNT = "repostsCount";
	public static final String ENDORSES_COUNT = "endorsesCount";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String ADDRESS = "address";
	public static final String URL = "url";
	public static final String TITLE = "title";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved CardBody entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param cardBody
	 *            CardBody entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CardBody cardBody) {
		LogUtil.log("saving CardBody instance", Level.INFO, null);
		try {
			entityManager.persist(cardBody);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent CardBody entity.
	 * 
	 * @param cardBody
	 *            CardBody entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CardBody cardBody) {
		LogUtil.log("deleting CardBody instance", Level.INFO, null);
		try {
			cardBody = entityManager.getReference(CardBody.class,
					cardBody.getCardBodyId());
			entityManager.remove(cardBody);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved CardBody entity and return it or a copy of it
	 * to the sender. A copy of the CardBody entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param cardBody
	 *            CardBody entity to update
	 * @return CardBody the persisted CardBody entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CardBody update(CardBody cardBody) {
		LogUtil.log("updating CardBody instance", Level.INFO, null);
		try {
			CardBody result = entityManager.merge(cardBody);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public CardBody findById(Long cardBodyId) {
		LogUtil.log("finding CardBody instance with id: " + cardBodyId,
				Level.INFO, null);
		try {
			CardBody instance = entityManager.find(CardBody.class, cardBodyId);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public CardBody getReference(Long cardBodyId) {
		LogUtil.log("getReferencing CardBody instance with id: " + cardBodyId,
				Level.INFO, null);
		try {
			CardBody instance = entityManager.getReference(CardBody.class,
					cardBodyId);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(CardBody cardBody) {
		LogUtil.log("detaching CardBody instance", Level.INFO, null);
		try {
			entityManager.detach(cardBody);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(CardBody cardBody) {
		LogUtil.log("refreshing CardBody instance", Level.INFO, null);
		try {
			entityManager.refresh(cardBody);
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

	public void remove(CardBody cardBody) {
		LogUtil.log("removing CardBody instance", Level.INFO, null);
		try {
			entityManager.remove(cardBody);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flushing CardBody instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void clear() {
		LogUtil.log("clearing CardBody instance", Level.INFO, null);
		try {
			entityManager.clear();
			LogUtil.log("clear successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("clear failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByCardBodyIdJPQL = "delete from CardBody a where a.cardBodyId in (?1)";

	public int removeByCardBodyId(Long cardBodyId) {
		LogUtil.log("removeByCardBodyId", Level.INFO, null);
		int ret = 0;
		try {
			Query query = entityManager.createQuery(removeByCardBodyIdJPQL);
			query.setParameter(1, cardBodyId);
			ret = query.executeUpdate();
			LogUtil.log("removeByCardBodyId successful", Level.INFO, null);
			return ret;
		} catch (RuntimeException re) {
			LogUtil.log("removeByCardBodyId failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String accountIdJPQL = "select a.account.accountId from CardBody a where a.cardBodyId = :cardBodyId";

	public java.lang.Integer getAccountId(java.lang.Long cardBodyId) {
		LogUtil.log("getAccountIdId with cardBodyId" + cardBodyId, Level.INFO,
				null);
		java.lang.Integer accountId;
		try {
			Query query = entityManager.createQuery(accountIdJPQL);
			query.setParameter("cardBodyId", cardBodyId);
			accountId = (java.lang.Integer) query.getSingleResult();
			LogUtil.log("getAccountIdId successful", Level.INFO, null);
			return accountId;
		} catch (RuntimeException re) {
			LogUtil.log("getAccountIdId failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseCommentsCountJPQL = "update CardBody a set a.commentsCount = a.commentsCount + :count where a.cardBodyId in (:cardBodyId)";

	public void increaseCommentsCount(java.lang.Long cardBodyId, int count) {
		LogUtil.log("increaseEndorsesCount with cardBodyId:" + cardBodyId,
				Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseCommentsCountJPQL);
			query.setParameter("cardBodyId", cardBodyId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseEndorsesCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseEndorsesCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseRepostsCountJPQL = "update CardBody a set a.repostsCount = a.repostsCount + :count where a.cardBodyId in (:cardBodyId)";

	public void increaseRepostsCount(java.lang.Long cardBodyId, int count) {
		LogUtil.log("increaseEndorsesCount with cardBodyId:" + cardBodyId,
				Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseRepostsCountJPQL);
			query.setParameter("cardBodyId", cardBodyId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseEndorsesCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseEndorsesCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseEndorsesCountJPQL = "update CardBody a set a.endorsesCount = a.endorsesCount + :count where a.cardBodyId in (:cardBodyId)";

	public void increaseEndorsesCount(java.lang.Long cardBodyId, int count) {
		LogUtil.log("increaseEndorsesCount with cardBodyId:" + cardBodyId,
				Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseEndorsesCountJPQL);
			query.setParameter("cardBodyId", cardBodyId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseEndorsesCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseEndorsesCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all CardBody entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CardBody property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<CardBody> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<CardBody> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding CardBody instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from CardBody model where model."
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

	public List<CardBody> findByCommentsCount(Object commentsCount,
			int... rowStartIdxAndCount) {
		return findByProperty(COMMENTS_COUNT, commentsCount,
				rowStartIdxAndCount);
	}

	public List<CardBody> findByRepostsCount(Object repostsCount,
			int... rowStartIdxAndCount) {
		return findByProperty(REPOSTS_COUNT, repostsCount, rowStartIdxAndCount);
	}

	public List<CardBody> findByEndorsesCount(Object endorsesCount,
			int... rowStartIdxAndCount) {
		return findByProperty(ENDORSES_COUNT, endorsesCount,
				rowStartIdxAndCount);
	}

	public List<CardBody> findByLatitude(Object latitude,
			int... rowStartIdxAndCount) {
		return findByProperty(LATITUDE, latitude, rowStartIdxAndCount);
	}

	public List<CardBody> findByLongitude(Object longitude,
			int... rowStartIdxAndCount) {
		return findByProperty(LONGITUDE, longitude, rowStartIdxAndCount);
	}

	public List<CardBody> findByAddress(Object address,
			int... rowStartIdxAndCount) {
		return findByProperty(ADDRESS, address, rowStartIdxAndCount);
	}

	public List<CardBody> findByUrl(Object url, int... rowStartIdxAndCount) {
		return findByProperty(URL, url, rowStartIdxAndCount);
	}

	public List<CardBody> findByTitle(Object title, int... rowStartIdxAndCount) {
		return findByProperty(TITLE, title, rowStartIdxAndCount);
	}

	/**
	 * Find all CardBody entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<CardBody> all CardBody entities
	 */
	@SuppressWarnings("unchecked")
	public List<CardBody> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all CardBody instances", Level.INFO, null);
		try {
			final String queryString = "select model from CardBody model";
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