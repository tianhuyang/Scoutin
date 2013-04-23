package com.scoutin.facades;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.scoutin.entities.Cardreposts;
import com.scoutin.entities.CardrepostsId;

/**
 * Facade for entity Cardreposts.
 * 
 * @see com.scoutin.entities.Cardreposts
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class CardrepostsFacade {
	// property constants
	public static final String COUNT = "count";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Cardreposts entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Cardreposts entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Cardreposts entity) {
		LogUtil.log("saving Cardreposts instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Cardreposts entity.
	 * 
	 * @param entity
	 *            Cardreposts entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Cardreposts entity) {
		LogUtil.log("deleting Cardreposts instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Cardreposts.class,
					entity.getId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Cardreposts entity and return it or a copy of
	 * it to the sender. A copy of the Cardreposts entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Cardreposts entity to update
	 * @return Cardreposts the persisted Cardreposts entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Cardreposts update(Cardreposts entity) {
		LogUtil.log("updating Cardreposts instance", Level.INFO, null);
		try {
			Cardreposts result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Cardreposts findById(CardrepostsId id) {
		LogUtil.log("finding Cardreposts instance with id: " + id, Level.INFO,
				null);
		try {
			Cardreposts instance = entityManager.find(Cardreposts.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Cardreposts entity) {
		LogUtil.log("detaching Cardreposts instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Cardreposts entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Cardreposts property to query
	 * @param value
	 *            the property value to match
	 * @return List<Cardreposts> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Cardreposts> findByProperty(String propertyName,
			final Object value) {
		LogUtil.log("finding Cardreposts instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Cardreposts model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Cardreposts> findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	/**
	 * Find all Cardreposts entities.
	 * 
	 * @return List<Cardreposts> all Cardreposts entities
	 */
	@SuppressWarnings("unchecked")
	public List<Cardreposts> findAll() {
		LogUtil.log("finding all Cardreposts instances", Level.INFO, null);
		try {
			final String queryString = "select model from Cardreposts model";
			Query query = entityManager.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}