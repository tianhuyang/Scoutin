package com.scoutin.facades;

import com.scoutin.entities.Profile;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Profile.
 * 
 * @see com.scoutin.entities.Profile
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class ProfileFacade {
	// property constants
	public static final String MIDDLENAME = "middlename";
	public static final String MOBILE = "mobile";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Profile entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param profile
	 *            Profile entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Profile profile) {
		LogUtil.log("saving Profile instance", Level.INFO, null);
		try {
			entityManager.persist(profile);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Profile entity.
	 * 
	 * @param profile
	 *            Profile entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Profile profile) {
		LogUtil.log("deleting Profile instance", Level.INFO, null);
		try {
			profile = entityManager.getReference(Profile.class,
					profile.getAccountId());
			entityManager.remove(profile);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Profile entity and return it or a copy of it
	 * to the sender. A copy of the Profile entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param profile
	 *            Profile entity to update
	 * @return Profile the persisted Profile entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Profile update(Profile profile) {
		LogUtil.log("updating Profile instance", Level.INFO, null);
		try {
			Profile result = entityManager.merge(profile);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Profile findById(Integer accountId) {
		LogUtil.log("finding Profile instance with id: " + accountId,
				Level.INFO, null);
		try {
			Profile instance = entityManager.find(Profile.class, accountId);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Profile getReference(Integer accountId) {
		LogUtil.log("getReferencing Profile instance with id: " + accountId,
				Level.INFO, null);
		try {
			Profile instance = entityManager.getReference(Profile.class,
					accountId);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Profile profile) {
		LogUtil.log("detaching Profile instance", Level.INFO, null);
		try {
			entityManager.detach(profile);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(Profile profile) {
		LogUtil.log("refreshing Profile instance", Level.INFO, null);
		try {
			entityManager.refresh(profile);
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

	public void remove(Profile profile) {
		LogUtil.log("removing Profile instance", Level.INFO, null);
		try {
			entityManager.remove(profile);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flushing Profile instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void clear() {
		LogUtil.log("clearing Profile instance", Level.INFO, null);
		try {
			entityManager.clear();
			LogUtil.log("clear successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("clear failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByAccountIdJPQL = "delete from Profile a where a.accountId in (?1)";

	public int removeByAccountId(Integer accountId) {
		LogUtil.log("removeByAccountId", Level.INFO, null);
		int ret = 0;
		try {
			Query query = entityManager.createQuery(removeByAccountIdJPQL);
			query.setParameter(1, accountId);
			ret = query.executeUpdate();
			LogUtil.log("removeByAccountId successful", Level.INFO, null);
			return ret;
		} catch (RuntimeException re) {
			LogUtil.log("removeByAccountId failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Profile entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Profile property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Profile> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Profile> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Profile instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Profile model where model."
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

	public List<Profile> findByMiddlename(Object middlename,
			int... rowStartIdxAndCount) {
		return findByProperty(MIDDLENAME, middlename, rowStartIdxAndCount);
	}

	public List<Profile> findByMobile(Object mobile, int... rowStartIdxAndCount) {
		return findByProperty(MOBILE, mobile, rowStartIdxAndCount);
	}

	/**
	 * Find all Profile entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Profile> all Profile entities
	 */
	@SuppressWarnings("unchecked")
	public List<Profile> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Profile instances", Level.INFO, null);
		try {
			final String queryString = "select model from Profile model";
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