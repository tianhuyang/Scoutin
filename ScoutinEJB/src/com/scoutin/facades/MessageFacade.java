package com.scoutin.facades;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.scoutin.entities.Message;

/**
 * Facade for entity Message.
 * 
 * @see com.scoutin.entities.Message
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class MessageFacade {
	// property constants

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Message entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Message entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Message entity) {
		LogUtil.log("saving Message instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Message entity.
	 * 
	 * @param entity
	 *            Message entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Message entity) {
		LogUtil.log("deleting Message instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Message.class,
					entity.getMessageId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Message entity and return it or a copy of it
	 * to the sender. A copy of the Message entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Message entity to update
	 * @return Message the persisted Message entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Message update(Message entity) {
		LogUtil.log("updating Message instance", Level.INFO, null);
		try {
			Message result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Message findById(Long id) {
		LogUtil.log("finding Message instance with id: " + id, Level.INFO, null);
		try {
			Message instance = entityManager.find(Message.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Message entity) {
		LogUtil.log("detaching Message instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Message entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Message property to query
	 * @param value
	 *            the property value to match
	 * @return List<Message> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Message> findByProperty(String propertyName, final Object value) {
		LogUtil.log("finding Message instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Message model where model."
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
	 * Find all Message entities.
	 * 
	 * @return List<Message> all Message entities
	 */
	@SuppressWarnings("unchecked")
	public List<Message> findAll() {
		LogUtil.log("finding all Message instances", Level.INFO, null);
		try {
			final String queryString = "select model from Message model";
			Query query = entityManager.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}