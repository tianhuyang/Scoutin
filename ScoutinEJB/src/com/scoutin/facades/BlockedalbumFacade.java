package com.scoutin.facades;

import com.scoutin.entities.Blockedalbum;
import com.scoutin.entities.BlockedalbumId;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Blockedalbum.
 * 
 * @see com.scoutin.entities.Blockedalbum
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class BlockedalbumFacade {
	// property constants

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Blockedalbum entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Blockedalbum entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Blockedalbum entity) {
		LogUtil.log("saving Blockedalbum instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Blockedalbum entity.
	 * 
	 * @param entity
	 *            Blockedalbum entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Blockedalbum entity) {
		LogUtil.log("deleting Blockedalbum instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Blockedalbum.class,
					entity.getId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Blockedalbum entity and return it or a copy of
	 * it to the sender. A copy of the Blockedalbum entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Blockedalbum entity to update
	 * @return Blockedalbum the persisted Blockedalbum entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Blockedalbum update(Blockedalbum entity) {
		LogUtil.log("updating Blockedalbum instance", Level.INFO, null);
		try {
			Blockedalbum result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Blockedalbum findById(BlockedalbumId id) {
		LogUtil.log("finding Blockedalbum instance with id: " + id, Level.INFO,
				null);
		try {
			Blockedalbum instance = entityManager.find(Blockedalbum.class, id);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Blockedalbum getReference(BlockedalbumId id) {
		LogUtil.log("getReferencing Blockedalbum instance with id: " + id,
				Level.INFO, null);
		try {
			Blockedalbum instance = entityManager.getReference(
					Blockedalbum.class, id);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Blockedalbum entity) {
		LogUtil.log("detaching Blockedalbum instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(Blockedalbum entity) {
		LogUtil.log("refreshing Blockedalbum instance", Level.INFO, null);
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

	public void remove(Blockedalbum entity) {
		LogUtil.log("removing Blockedalbum instance", Level.INFO, null);
		try {
			entityManager.remove(entity);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flush Blockedalbum instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Blockedalbum entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Blockedalbum property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Blockedalbum> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Blockedalbum> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Blockedalbum instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Blockedalbum model where model."
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
	 * Find all Blockedalbum entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Blockedalbum> all Blockedalbum entities
	 */
	@SuppressWarnings("unchecked")
	public List<Blockedalbum> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Blockedalbum instances", Level.INFO, null);
		try {
			final String queryString = "select model from Blockedalbum model";
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