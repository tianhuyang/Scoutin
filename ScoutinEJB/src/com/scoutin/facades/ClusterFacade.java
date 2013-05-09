package com.scoutin.facades;

import com.scoutin.entities.Cluster;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Cluster.
 * 
 * @see com.scoutin.entities.Cluster
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class ClusterFacade {
	// property constants
	public static final String NAME = "name";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Cluster entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param cluster
	 *            Cluster entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Cluster cluster) {
		LogUtil.log("saving Cluster instance", Level.INFO, null);
		try {
			entityManager.persist(cluster);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Cluster entity.
	 * 
	 * @param cluster
	 *            Cluster entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Cluster cluster) {
		LogUtil.log("deleting Cluster instance", Level.INFO, null);
		try {
			cluster = entityManager.getReference(Cluster.class,
					cluster.getClusterId());
			entityManager.remove(cluster);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Cluster entity and return it or a copy of it
	 * to the sender. A copy of the Cluster entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param cluster
	 *            Cluster entity to update
	 * @return Cluster the persisted Cluster entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Cluster update(Cluster cluster) {
		LogUtil.log("updating Cluster instance", Level.INFO, null);
		try {
			Cluster result = entityManager.merge(cluster);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Cluster findById(Long clusterId) {
		LogUtil.log("finding Cluster instance with id: " + clusterId,
				Level.INFO, null);
		try {
			Cluster instance = entityManager.find(Cluster.class, clusterId);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Cluster getReference(Long clusterId) {
		LogUtil.log("getReferencing Cluster instance with id: " + clusterId,
				Level.INFO, null);
		try {
			Cluster instance = entityManager.getReference(Cluster.class,
					clusterId);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Cluster cluster) {
		LogUtil.log("detaching Cluster instance", Level.INFO, null);
		try {
			entityManager.detach(cluster);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(Cluster cluster) {
		LogUtil.log("refreshing Cluster instance", Level.INFO, null);
		try {
			entityManager.refresh(cluster);
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

	public void remove(Cluster cluster) {
		LogUtil.log("removing Cluster instance", Level.INFO, null);
		try {
			entityManager.remove(cluster);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flush Cluster instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByClusterIdJPQL = "delete from Cluster a where a.clusterId in (?1)";

	public void removeByClusterId(Long clusterId) {
		LogUtil.log("removeByClusterId", Level.INFO, null);
		try {
			Query query = entityManager.createQuery(removeByClusterIdJPQL);
			query.setParameter(1, clusterId);
			query.executeUpdate();
			LogUtil.log("removeByClusterId successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("removeByClusterId failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String accountIdJPQL = "select a.account.accountId from Cluster a where a.clusterId = :clusterId";

	public java.lang.Integer getAccountId(java.lang.Long clusterId) {
		LogUtil.log("getAccountIdId with clusterId" + clusterId, Level.INFO,
				null);
		java.lang.Integer accountId;
		try {
			Query query = entityManager.createQuery(accountIdJPQL);
			query.setParameter("clusterId", clusterId);
			accountId = (java.lang.Integer) query.getSingleResult();
			LogUtil.log("getAccountIdId successful", Level.INFO, null);
			return accountId;
		} catch (RuntimeException re) {
			LogUtil.log("getAccountIdId failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Cluster entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Cluster property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Cluster> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Cluster> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Cluster instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Cluster model where model."
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

	public List<Cluster> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}

	/**
	 * Find all Cluster entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Cluster> all Cluster entities
	 */
	@SuppressWarnings("unchecked")
	public List<Cluster> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Cluster instances", Level.INFO, null);
		try {
			final String queryString = "select model from Cluster model";
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