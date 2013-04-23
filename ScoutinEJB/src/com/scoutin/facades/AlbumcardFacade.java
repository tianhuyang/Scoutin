package com.scoutin.facades;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.scoutin.entities.Albumcard;
import com.scoutin.entities.AlbumcardId;

/**
 * Facade for entity Albumcard.
 * 
 * @see com.scoutin.entities.Albumcard
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class AlbumcardFacade {
	// property constants

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Albumcard entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Albumcard entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Albumcard entity) {
		LogUtil.log("saving Albumcard instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Albumcard entity.
	 * 
	 * @param entity
	 *            Albumcard entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Albumcard entity) {
		LogUtil.log("deleting Albumcard instance", Level.INFO, null);
		try {
			entity = entityManager
					.getReference(Albumcard.class, entity.getId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Albumcard entity and return it or a copy of it
	 * to the sender. A copy of the Albumcard entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Albumcard entity to update
	 * @return Albumcard the persisted Albumcard entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Albumcard update(Albumcard entity) {
		LogUtil.log("updating Albumcard instance", Level.INFO, null);
		try {
			Albumcard result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Albumcard findById(AlbumcardId id) {
		LogUtil.log("finding Albumcard instance with id: " + id, Level.INFO,
				null);
		try {
			Albumcard instance = entityManager.find(Albumcard.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Albumcard entity) {
		LogUtil.log("detaching Albumcard instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Albumcard entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Albumcard property to query
	 * @param value
	 *            the property value to match
	 * @return List<Albumcard> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Albumcard> findByProperty(String propertyName,
			final Object value) {
		LogUtil.log("finding Albumcard instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Albumcard model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Albumcard entities.
	 * 
	 * @return List<Albumcard> all Albumcard entities
	 */
	@SuppressWarnings("unchecked")
	public List<Albumcard> findAll() {
		LogUtil.log("finding all Albumcard instances", Level.INFO, null);
		try {
			final String queryString = "select model from Albumcard model";
			Query query = entityManager.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}