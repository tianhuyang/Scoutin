package com.scoutin.facades;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.scoutin.entities.Cardrepost;
import com.scoutin.entities.CardrepostId;

/**
 * Facade for entity Cardrepost.
 * 
 * @see com.scoutin.entities.Cardrepost
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class CardrepostFacade {
	// property constants
	public static final String COUNT = "count";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Cardrepost entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Cardrepost entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Cardrepost entity) {
		LogUtil.log("saving Cardrepost instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Cardrepost entity.
	 * 
	 * @param entity
	 *            Cardrepost entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Cardrepost entity) {
		LogUtil.log("deleting Cardrepost instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Cardrepost.class,
					entity.getId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Cardrepost entity and return it or a copy of
	 * it to the sender. A copy of the Cardrepost entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Cardrepost entity to update
	 * @return Cardrepost the persisted Cardrepost entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Cardrepost update(Cardrepost entity) {
		LogUtil.log("updating Cardrepost instance", Level.INFO, null);
		try {
			Cardrepost result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Cardrepost findById(CardrepostId id) {
		LogUtil.log("finding Cardrepost instance with id: " + id, Level.INFO,
				null);
		try {
			Cardrepost instance = entityManager.find(Cardrepost.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Cardrepost entity) {
		LogUtil.log("detaching Cardrepost instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Cardrepost entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Cardrepost property to query
	 * @param value
	 *            the property value to match
	 * @return List<Cardrepost> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Cardrepost> findByProperty(String propertyName,
			final Object value) {
		LogUtil.log("finding Cardrepost instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Cardrepost model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Cardrepost> findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	/**
	 * Find all Cardrepost entities.
	 * 
	 * @return List<Cardrepost> all Cardrepost entities
	 */
	@SuppressWarnings("unchecked")
	public List<Cardrepost> findAll() {
		LogUtil.log("finding all Cardrepost instances", Level.INFO, null);
		try {
			final String queryString = "select model from Cardrepost model";
			Query query = entityManager.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}