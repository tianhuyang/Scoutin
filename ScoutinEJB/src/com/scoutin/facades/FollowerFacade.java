package com.scoutin.facades;

import com.scoutin.entities.Follower;
import com.scoutin.entities.FollowerId;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Follower.
 * 
 * @see com.scoutin.entities.Follower
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class FollowerFacade {
	// property constants

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Follower entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param follower
	 *            Follower entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Follower follower) {
		LogUtil.log("saving Follower instance", Level.INFO, null);
		try {
			entityManager.persist(follower);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Follower entity.
	 * 
	 * @param follower
	 *            Follower entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Follower follower) {
		LogUtil.log("deleting Follower instance", Level.INFO, null);
		try {
			follower = entityManager.getReference(Follower.class,
					follower.getId());
			entityManager.remove(follower);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Follower entity and return it or a copy of it
	 * to the sender. A copy of the Follower entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param follower
	 *            Follower entity to update
	 * @return Follower the persisted Follower entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Follower update(Follower follower) {
		LogUtil.log("updating Follower instance", Level.INFO, null);
		try {
			Follower result = entityManager.merge(follower);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Follower findById(FollowerId id) {
		LogUtil.log("finding Follower instance with id: " + id, Level.INFO,
				null);
		try {
			Follower instance = entityManager.find(Follower.class, id);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Follower getReference(FollowerId id) {
		LogUtil.log("getReferencing Follower instance with id: " + id,
				Level.INFO, null);
		try {
			Follower instance = entityManager.getReference(Follower.class, id);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Follower follower) {
		LogUtil.log("detaching Follower instance", Level.INFO, null);
		try {
			entityManager.detach(follower);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(Follower follower) {
		LogUtil.log("refreshing Follower instance", Level.INFO, null);
		try {
			entityManager.refresh(follower);
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

	public void remove(Follower follower) {
		LogUtil.log("removing Follower instance", Level.INFO, null);
		try {
			entityManager.remove(follower);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flushing Follower instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void clear() {
		LogUtil.log("clearing Follower instance", Level.INFO, null);
		try {
			entityManager.clear();
			LogUtil.log("clear successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("clear failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByIdJPQL = "delete from Follower a where a.id in (?1)";

	public int removeById(FollowerId id) {
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
	 * Find all Follower entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Follower property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Follower> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Follower> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Follower instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Follower model where model."
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
	 * Find all Follower entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Follower> all Follower entities
	 */
	@SuppressWarnings("unchecked")
	public List<Follower> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Follower instances", Level.INFO, null);
		try {
			final String queryString = "select model from Follower model";
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