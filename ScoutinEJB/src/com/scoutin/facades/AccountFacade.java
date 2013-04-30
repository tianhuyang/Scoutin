package com.scoutin.facades;

import com.scoutin.entities.Account;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Account.
 * 
 * @see com.scoutin.entities.Account
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class AccountFacade {
	// property constants
	public static final String VERSION = "version";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String FACEBOOK_ID = "facebookId";
	public static final String TWITTER_ID = "twitterId";
	public static final String FIRSTNAME = "firstname";
	public static final String LASTNAME = "lastname";
	public static final String SEX = "sex";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Account entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Account entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Account entity) {
		LogUtil.log("saving Account instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Account entity.
	 * 
	 * @param entity
	 *            Account entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Account entity) {
		LogUtil.log("deleting Account instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Account.class,
					entity.getAccountId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Account entity and return it or a copy of it
	 * to the sender. A copy of the Account entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Account entity to update
	 * @return Account the persisted Account entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Account update(Account entity) {
		LogUtil.log("updating Account instance", Level.INFO, null);
		try {
			Account result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Account findById(Integer id) {
		LogUtil.log("finding Account instance with id: " + id, Level.INFO, null);
		try {
			Account instance = entityManager.find(Account.class, id);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Account getReference(Integer id) {
		LogUtil.log("getReferencing Account instance with id: " + id,
				Level.INFO, null);
		try {
			Account instance = entityManager.getReference(Account.class, id);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Account entity) {
		LogUtil.log("detaching Account instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(Account entity) {
		LogUtil.log("refreshing Account instance", Level.INFO, null);
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

	public void remove(Account entity) {
		LogUtil.log("removing Account instance", Level.INFO, null);
		try {
			entityManager.remove(entity);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flush Account instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Account entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Account property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Account> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Account> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Account instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Account model where model."
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

	public List<Account> findByVersion(Object version,
			int... rowStartIdxAndCount) {
		return findByProperty(VERSION, version, rowStartIdxAndCount);
	}

	public List<Account> findByPassword(Object password,
			int... rowStartIdxAndCount) {
		return findByProperty(PASSWORD, password, rowStartIdxAndCount);
	}

	public List<Account> findByEmail(Object email, int... rowStartIdxAndCount) {
		return findByProperty(EMAIL, email, rowStartIdxAndCount);
	}

	public List<Account> findByFacebookId(Object facebookId,
			int... rowStartIdxAndCount) {
		return findByProperty(FACEBOOK_ID, facebookId, rowStartIdxAndCount);
	}

	public List<Account> findByTwitterId(Object twitterId,
			int... rowStartIdxAndCount) {
		return findByProperty(TWITTER_ID, twitterId, rowStartIdxAndCount);
	}

	public List<Account> findByFirstname(Object firstname,
			int... rowStartIdxAndCount) {
		return findByProperty(FIRSTNAME, firstname, rowStartIdxAndCount);
	}

	public List<Account> findByLastname(Object lastname,
			int... rowStartIdxAndCount) {
		return findByProperty(LASTNAME, lastname, rowStartIdxAndCount);
	}

	public List<Account> findBySex(Object sex, int... rowStartIdxAndCount) {
		return findByProperty(SEX, sex, rowStartIdxAndCount);
	}

	/**
	 * Find all Account entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Account> all Account entities
	 */
	@SuppressWarnings("unchecked")
	public List<Account> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Account instances", Level.INFO, null);
		try {
			final String queryString = "select model from Account model";
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