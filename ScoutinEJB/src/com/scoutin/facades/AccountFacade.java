package com.scoutin.facades;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.scoutin.entities.Account;

/**
 * Facade for entity Account.
 * 
 * @see com.scoutin.entities.Account
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class AccountFacade {
	// property constants
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
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Account entity) {
		LogUtil.log("detaching Account instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
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
	 * @return List<Account> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Account> findByProperty(String propertyName, final Object value) {
		LogUtil.log("finding Account instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Account model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Account> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<Account> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<Account> findByFacebookId(Object facebookId) {
		return findByProperty(FACEBOOK_ID, facebookId);
	}

	public List<Account> findByTwitterId(Object twitterId) {
		return findByProperty(TWITTER_ID, twitterId);
	}

	public List<Account> findByFirstname(Object firstname) {
		return findByProperty(FIRSTNAME, firstname);
	}

	public List<Account> findByLastname(Object lastname) {
		return findByProperty(LASTNAME, lastname);
	}

	public List<Account> findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	/**
	 * Find all Account entities.
	 * 
	 * @return List<Account> all Account entities
	 */
	@SuppressWarnings("unchecked")
	public List<Account> findAll() {
		LogUtil.log("finding all Account instances", Level.INFO, null);
		try {
			final String queryString = "select model from Account model";
			Query query = entityManager.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}