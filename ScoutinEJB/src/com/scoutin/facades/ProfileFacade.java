package com.scoutin.facades;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.scoutin.entities.Profile;

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
	 * @param entity
	 *            Profile entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Profile entity) {
		LogUtil.log("saving Profile instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Profile entity.
	 * 
	 * @param entity
	 *            Profile entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Profile entity) {
		LogUtil.log("deleting Profile instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Profile.class,
					entity.getAccountId());
			entityManager.remove(entity);
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
	 * @param entity
	 *            Profile entity to update
	 * @return Profile the persisted Profile entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Profile update(Profile entity) {
		LogUtil.log("updating Profile instance", Level.INFO, null);
		try {
			Profile result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Profile findById(Integer id) {
		LogUtil.log("finding Profile instance with id: " + id, Level.INFO, null);
		try {
			Profile instance = entityManager.find(Profile.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Profile entity) {
		LogUtil.log("detaching Profile instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
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
	 * @return List<Profile> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Profile> findByProperty(String propertyName, final Object value) {
		LogUtil.log("finding Profile instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Profile model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Profile> findByMiddlename(Object middlename) {
		return findByProperty(MIDDLENAME, middlename);
	}

	public List<Profile> findByMobile(Object mobile) {
		return findByProperty(MOBILE, mobile);
	}

	/**
	 * Find all Profile entities.
	 * 
	 * @return List<Profile> all Profile entities
	 */
	@SuppressWarnings("unchecked")
	public List<Profile> findAll() {
		LogUtil.log("finding all Profile instances", Level.INFO, null);
		try {
			final String queryString = "select model from Profile model";
			Query query = entityManager.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}