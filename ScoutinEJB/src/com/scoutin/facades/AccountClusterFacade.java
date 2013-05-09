package com.scoutin.facades;

import com.scoutin.entities.AccountCluster;
import com.scoutin.entities.AccountClusterId;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity AccountCluster.
 * 
 * @see com.scoutin.entities.AccountCluster
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class AccountClusterFacade {
	// property constants

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved AccountCluster entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param accountCluster
	 *            AccountCluster entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AccountCluster accountCluster) {
		LogUtil.log("saving AccountCluster instance", Level.INFO, null);
		try {
			entityManager.persist(accountCluster);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent AccountCluster entity.
	 * 
	 * @param accountCluster
	 *            AccountCluster entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AccountCluster accountCluster) {
		LogUtil.log("deleting AccountCluster instance", Level.INFO, null);
		try {
			accountCluster = entityManager.getReference(AccountCluster.class,
					accountCluster.getId());
			entityManager.remove(accountCluster);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved AccountCluster entity and return it or a copy
	 * of it to the sender. A copy of the AccountCluster entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity.
	 * 
	 * @param accountCluster
	 *            AccountCluster entity to update
	 * @return AccountCluster the persisted AccountCluster entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AccountCluster update(AccountCluster accountCluster) {
		LogUtil.log("updating AccountCluster instance", Level.INFO, null);
		try {
			AccountCluster result = entityManager.merge(accountCluster);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public AccountCluster findById(AccountClusterId id) {
		LogUtil.log("finding AccountCluster instance with id: " + id,
				Level.INFO, null);
		try {
			AccountCluster instance = entityManager.find(AccountCluster.class,
					id);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public AccountCluster getReference(AccountClusterId id) {
		LogUtil.log("getReferencing AccountCluster instance with id: " + id,
				Level.INFO, null);
		try {
			AccountCluster instance = entityManager.getReference(
					AccountCluster.class, id);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(AccountCluster accountCluster) {
		LogUtil.log("detaching AccountCluster instance", Level.INFO, null);
		try {
			entityManager.detach(accountCluster);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(AccountCluster accountCluster) {
		LogUtil.log("refreshing AccountCluster instance", Level.INFO, null);
		try {
			entityManager.refresh(accountCluster);
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

	public void remove(AccountCluster accountCluster) {
		LogUtil.log("removing AccountCluster instance", Level.INFO, null);
		try {
			entityManager.remove(accountCluster);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flush AccountCluster instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByIdJPQL = "delete from AccountCluster a where a.id in (?1)";

	public void removeById(AccountClusterId id) {
		LogUtil.log("removeById", Level.INFO, null);
		try {
			Query query = entityManager.createQuery(removeByIdJPQL);
			query.setParameter(1, id);
			query.executeUpdate();
			LogUtil.log("removeById successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("removeById failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all AccountCluster entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AccountCluster property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<AccountCluster> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<AccountCluster> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding AccountCluster instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from AccountCluster model where model."
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
	 * Find all AccountCluster entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AccountCluster> all AccountCluster entities
	 */
	@SuppressWarnings("unchecked")
	public List<AccountCluster> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all AccountCluster instances", Level.INFO, null);
		try {
			final String queryString = "select model from AccountCluster model";
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