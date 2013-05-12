package com.scoutin.facades;

import com.scoutin.entities.AccountStat;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity AccountStat.
 * 
 * @see com.scoutin.entities.AccountStat
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class AccountStatFacade {
	// property constants
	public static final String FOLLOWING_COUNT = "followingCount";
	public static final String FOLLOWERS_COUNT = "followersCount";
	public static final String UNVIEW_RECMD_COUNT = "unviewRecmdCount";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved AccountStat entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param accountStat
	 *            AccountStat entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AccountStat accountStat) {
		LogUtil.log("saving AccountStat instance", Level.INFO, null);
		try {
			entityManager.persist(accountStat);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent AccountStat entity.
	 * 
	 * @param accountStat
	 *            AccountStat entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AccountStat accountStat) {
		LogUtil.log("deleting AccountStat instance", Level.INFO, null);
		try {
			accountStat = entityManager.getReference(AccountStat.class,
					accountStat.getAccountId());
			entityManager.remove(accountStat);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved AccountStat entity and return it or a copy of
	 * it to the sender. A copy of the AccountStat entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param accountStat
	 *            AccountStat entity to update
	 * @return AccountStat the persisted AccountStat entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AccountStat update(AccountStat accountStat) {
		LogUtil.log("updating AccountStat instance", Level.INFO, null);
		try {
			AccountStat result = entityManager.merge(accountStat);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public AccountStat findById(Integer accountId) {
		LogUtil.log("finding AccountStat instance with id: " + accountId,
				Level.INFO, null);
		try {
			AccountStat instance = entityManager.find(AccountStat.class,
					accountId);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public AccountStat getReference(Integer accountId) {
		LogUtil.log(
				"getReferencing AccountStat instance with id: " + accountId,
				Level.INFO, null);
		try {
			AccountStat instance = entityManager.getReference(
					AccountStat.class, accountId);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(AccountStat accountStat) {
		LogUtil.log("detaching AccountStat instance", Level.INFO, null);
		try {
			entityManager.detach(accountStat);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(AccountStat accountStat) {
		LogUtil.log("refreshing AccountStat instance", Level.INFO, null);
		try {
			entityManager.refresh(accountStat);
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

	public void remove(AccountStat accountStat) {
		LogUtil.log("removing AccountStat instance", Level.INFO, null);
		try {
			entityManager.remove(accountStat);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flushing AccountStat instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void clear() {
		LogUtil.log("clearing AccountStat instance", Level.INFO, null);
		try {
			entityManager.clear();
			LogUtil.log("clear successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("clear failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByAccountIdJPQL = "delete from AccountStat a where a.accountId in (?1)";

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

	private static final String increaseFollowingCountJPQL = "update AccountStat a set a.followingCount = a.followingCount + :count where a.accountId in (:accountId)";

	public void increaseFollowingCount(java.lang.Integer accountId, int count) {
		LogUtil.log("increaseUnviewRecmdCount with accountId:" + accountId,
				Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseFollowingCountJPQL);
			query.setParameter("accountId", accountId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseUnviewRecmdCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseUnviewRecmdCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseFollowersCountJPQL = "update AccountStat a set a.followersCount = a.followersCount + :count where a.accountId in (:accountId)";

	public void increaseFollowersCount(java.lang.Integer accountId, int count) {
		LogUtil.log("increaseUnviewRecmdCount with accountId:" + accountId,
				Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseFollowersCountJPQL);
			query.setParameter("accountId", accountId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseUnviewRecmdCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseUnviewRecmdCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseUnviewRecmdCountJPQL = "update AccountStat a set a.unviewRecmdCount = a.unviewRecmdCount + :count where a.accountId in (:accountId)";

	public void increaseUnviewRecmdCount(java.lang.Integer accountId, int count) {
		LogUtil.log("increaseUnviewRecmdCount with accountId:" + accountId,
				Level.INFO, null);
		try {
			Query query = entityManager
					.createQuery(increaseUnviewRecmdCountJPQL);
			query.setParameter("accountId", accountId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseUnviewRecmdCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseUnviewRecmdCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all AccountStat entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AccountStat property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<AccountStat> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<AccountStat> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding AccountStat instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from AccountStat model where model."
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

	public List<AccountStat> findByFollowingCount(Object followingCount,
			int... rowStartIdxAndCount) {
		return findByProperty(FOLLOWING_COUNT, followingCount,
				rowStartIdxAndCount);
	}

	public List<AccountStat> findByFollowersCount(Object followersCount,
			int... rowStartIdxAndCount) {
		return findByProperty(FOLLOWERS_COUNT, followersCount,
				rowStartIdxAndCount);
	}

	public List<AccountStat> findByUnviewRecmdCount(Object unviewRecmdCount,
			int... rowStartIdxAndCount) {
		return findByProperty(UNVIEW_RECMD_COUNT, unviewRecmdCount,
				rowStartIdxAndCount);
	}

	/**
	 * Find all AccountStat entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AccountStat> all AccountStat entities
	 */
	@SuppressWarnings("unchecked")
	public List<AccountStat> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all AccountStat instances", Level.INFO, null);
		try {
			final String queryString = "select model from AccountStat model";
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