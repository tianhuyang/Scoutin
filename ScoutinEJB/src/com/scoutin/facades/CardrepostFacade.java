package com.scoutin.facades;

import com.scoutin.entities.Cardrepost;
import com.scoutin.entities.CardrepostId;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Cardrepost.
 * 
 * @see com.scoutin.entities.Cardrepost
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class CardrepostFacade {
	// property constants
	public static final String COUNT = "count";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Cardrepost entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Cardrepost entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Cardrepost entity) {
		LogUtil.log("saving Cardrepost instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Cardrepost entity.
	 * 
	 * @param entity
	 *            Cardrepost entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Cardrepost entity) {
		LogUtil.log("deleting Cardrepost instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Cardrepost.class,
					entity.getId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Cardrepost entity and return it or a copy of
	 * it to the sender. A copy of the Cardrepost entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Cardrepost entity to update
	 * @return Cardrepost the persisted Cardrepost entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Cardrepost update(Cardrepost entity) {
		LogUtil.log("updating Cardrepost instance", Level.INFO, null);
		try {
			Cardrepost result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Cardrepost findById(CardrepostId id) {
		LogUtil.log("finding Cardrepost instance with id: " + id, Level.INFO,
				null);
		try {
			Cardrepost instance = entityManager.find(Cardrepost.class, id);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Cardrepost getReference(CardrepostId id) {
		LogUtil.log("getReferencing Cardrepost instance with id: " + id,
				Level.INFO, null);
		try {
			Cardrepost instance = entityManager.getReference(Cardrepost.class,
					id);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Cardrepost entity) {
		LogUtil.log("detaching Cardrepost instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(Cardrepost entity) {
		LogUtil.log("refreshing Cardrepost instance", Level.INFO, null);
		try {
			entityManager.refresh(entity);
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

	public void remove(Cardrepost entity) {
		LogUtil.log("removing Cardrepost instance", Level.INFO, null);
		try {
			entityManager.remove(entity);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flush Cardrepost instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseCountJPQL = "update Cardrepost a set a.count = a.count + :count where a.id in (:id)";

	public void increaseCount(com.scoutin.entities.CardrepostId id, int count) {
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
	 * Find all Cardrepost entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Cardrepost property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Cardrepost> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Cardrepost> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Cardrepost instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Cardrepost model where model."
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

	public List<Cardrepost> findByCount(Object count,
			int... rowStartIdxAndCount) {
		return findByProperty(COUNT, count, rowStartIdxAndCount);
	}

	/**
	 * Find all Cardrepost entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Cardrepost> all Cardrepost entities
	 */
	@SuppressWarnings("unchecked")
	public List<Cardrepost> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Cardrepost instances", Level.INFO, null);
		try {
			final String queryString = "select model from Cardrepost model";
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