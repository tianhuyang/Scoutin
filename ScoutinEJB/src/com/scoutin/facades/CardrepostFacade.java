package com.scoutin.facades;

import com.scoutin.entities.CardRepost;
import com.scoutin.entities.CardRepostId;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity CardRepost.
 * 
 * @see com.scoutin.entities.CardRepost
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class CardRepostFacade {
	// property constants
	public static final String COUNT = "count";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved CardRepost entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param cardRepost
	 *            CardRepost entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CardRepost cardRepost) {
		LogUtil.log("saving CardRepost instance", Level.INFO, null);
		try {
			entityManager.persist(cardRepost);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent CardRepost entity.
	 * 
	 * @param cardRepost
	 *            CardRepost entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CardRepost cardRepost) {
		LogUtil.log("deleting CardRepost instance", Level.INFO, null);
		try {
			cardRepost = entityManager.getReference(CardRepost.class,
					cardRepost.getId());
			entityManager.remove(cardRepost);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved CardRepost entity and return it or a copy of
	 * it to the sender. A copy of the CardRepost entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param cardRepost
	 *            CardRepost entity to update
	 * @return CardRepost the persisted CardRepost entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CardRepost update(CardRepost cardRepost) {
		LogUtil.log("updating CardRepost instance", Level.INFO, null);
		try {
			CardRepost result = entityManager.merge(cardRepost);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public CardRepost findById(CardRepostId id) {
		LogUtil.log("finding CardRepost instance with id: " + id, Level.INFO,
				null);
		try {
			CardRepost instance = entityManager.find(CardRepost.class, id);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public CardRepost getReference(CardRepostId id) {
		LogUtil.log("getReferencing CardRepost instance with id: " + id,
				Level.INFO, null);
		try {
			CardRepost instance = entityManager.getReference(CardRepost.class,
					id);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(CardRepost cardRepost) {
		LogUtil.log("detaching CardRepost instance", Level.INFO, null);
		try {
			entityManager.detach(cardRepost);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(CardRepost cardRepost) {
		LogUtil.log("refreshing CardRepost instance", Level.INFO, null);
		try {
			entityManager.refresh(cardRepost);
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

	public void remove(CardRepost cardRepost) {
		LogUtil.log("removing CardRepost instance", Level.INFO, null);
		try {
			entityManager.remove(cardRepost);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flushing CardRepost instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void clear() {
		LogUtil.log("clearing CardRepost instance", Level.INFO, null);
		try {
			entityManager.clear();
			LogUtil.log("clear successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("clear failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByIdJPQL = "delete from CardRepost a where a.id in (?1)";

	public int removeById(CardRepostId id) {
		LogUtil.log("removeById", Level.INFO, null);
		int ret = 0;
		try {
			Query query = entityManager.createQuery(removeByIdJPQL);
			query.setParameter(1, id);
			ret = query.executeUpdate();
			LogUtil.log("removeById successful", Level.INFO, null);
			return ret;
		} catch (RuntimeException re) {
			LogUtil.log("removeById failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseCountJPQL = "update CardRepost a set a.count = a.count + :count where a.id in (:id)";

	public void increaseCount(com.scoutin.entities.CardRepostId id, int count) {
		LogUtil.log("increaseCount with id:" + id, Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseCountJPQL);
			query.setParameter("id", id);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all CardRepost entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CardRepost property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<CardRepost> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<CardRepost> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding CardRepost instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from CardRepost model where model."
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

	public List<CardRepost> findByCount(Object count,
			int... rowStartIdxAndCount) {
		return findByProperty(COUNT, count, rowStartIdxAndCount);
	}

	/**
	 * Find all CardRepost entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<CardRepost> all CardRepost entities
	 */
	@SuppressWarnings("unchecked")
	public List<CardRepost> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all CardRepost instances", Level.INFO, null);
		try {
			final String queryString = "select model from CardRepost model";
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