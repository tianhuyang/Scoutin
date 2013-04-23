package com.scoutin.facades;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.scoutin.entities.Notification;

/**
 * Facade for entity Notification.
 * 
 * @see com.scoutin.entities.Notification
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class NotificationFacade {
	// property constants

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Notification entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Notification entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Notification entity) {
		LogUtil.log("saving Notification instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Notification entity.
	 * 
	 * @param entity
	 *            Notification entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Notification entity) {
		LogUtil.log("deleting Notification instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Notification.class,
					entity.getNotificationId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Notification entity and return it or a copy of
	 * it to the sender. A copy of the Notification entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Notification entity to update
	 * @return Notification the persisted Notification entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Notification update(Notification entity) {
		LogUtil.log("updating Notification instance", Level.INFO, null);
		try {
			Notification result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Notification findById(Long id) {
		LogUtil.log("finding Notification instance with id: " + id, Level.INFO,
				null);
		try {
			Notification instance = entityManager.find(Notification.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Notification entity) {
		LogUtil.log("detaching Notification instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Notification entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Notification property to query
	 * @param value
	 *            the property value to match
	 * @return List<Notification> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Notification> findByProperty(String propertyName,
			final Object value) {
		LogUtil.log("finding Notification instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Notification model where model."
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
	 * Find all Notification entities.
	 * 
	 * @return List<Notification> all Notification entities
	 */
	@SuppressWarnings("unchecked")
	public List<Notification> findAll() {
		LogUtil.log("finding all Notification instances", Level.INFO, null);
		try {
			final String queryString = "select model from Notification model";
			Query query = entityManager.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}