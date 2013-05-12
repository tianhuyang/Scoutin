package com.scoutin.facades;

import com.scoutin.entities.CardEndorse;
import com.scoutin.entities.CardEndorseId;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity CardEndorse.
 * 
 * @see com.scoutin.entities.CardEndorse
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class CardEndorseFacade {
	// property constants

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved CardEndorse entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param cardEndorse
	 *            CardEndorse entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CardEndorse cardEndorse) {
		LogUtil.log("saving CardEndorse instance", Level.INFO, null);
		try {
			entityManager.persist(cardEndorse);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent CardEndorse entity.
	 * 
	 * @param cardEndorse
	 *            CardEndorse entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CardEndorse cardEndorse) {
		LogUtil.log("deleting CardEndorse instance", Level.INFO, null);
		try {
			cardEndorse = entityManager.getReference(CardEndorse.class,
					cardEndorse.getId());
			entityManager.remove(cardEndorse);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved CardEndorse entity and return it or a copy of
	 * it to the sender. A copy of the CardEndorse entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param cardEndorse
	 *            CardEndorse entity to update
	 * @return CardEndorse the persisted CardEndorse entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CardEndorse update(CardEndorse cardEndorse) {
		LogUtil.log("updating CardEndorse instance", Level.INFO, null);
		try {
			CardEndorse result = entityManager.merge(cardEndorse);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public CardEndorse findById(CardEndorseId id) {
		LogUtil.log("finding CardEndorse instance with id: " + id, Level.INFO,
				null);
		try {
			CardEndorse instance = entityManager.find(CardEndorse.class, id);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public CardEndorse getReference(CardEndorseId id) {
		LogUtil.log("getReferencing CardEndorse instance with id: " + id,
				Level.INFO, null);
		try {
			CardEndorse instance = entityManager.getReference(
					CardEndorse.class, id);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(CardEndorse cardEndorse) {
		LogUtil.log("detaching CardEndorse instance", Level.INFO, null);
		try {
			entityManager.detach(cardEndorse);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(CardEndorse cardEndorse) {
		LogUtil.log("refreshing CardEndorse instance", Level.INFO, null);
		try {
			entityManager.refresh(cardEndorse);
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

	public void remove(CardEndorse cardEndorse) {
		LogUtil.log("removing CardEndorse instance", Level.INFO, null);
		try {
			entityManager.remove(cardEndorse);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flushing CardEndorse instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void clear() {
		LogUtil.log("clearing CardEndorse instance", Level.INFO, null);
		try {
			entityManager.clear();
			LogUtil.log("clear successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("clear failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByIdJPQL = "delete from CardEndorse a where a.id in (?1)";

	public int removeById(CardEndorseId id) {
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

	/**
	 * Find all CardEndorse entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CardEndorse property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<CardEndorse> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<CardEndorse> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding CardEndorse instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from CardEndorse model where model."
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

	/**
	 * Find all CardEndorse entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<CardEndorse> all CardEndorse entities
	 */
	@SuppressWarnings("unchecked")
	public List<CardEndorse> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all CardEndorse instances", Level.INFO, null);
		try {
			final String queryString = "select model from CardEndorse model";
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