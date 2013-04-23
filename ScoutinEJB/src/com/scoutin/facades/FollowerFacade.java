package com.scoutin.facades;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.scoutin.entities.Follower;
import com.scoutin.entities.FollowerId;

/**
 * Facade for entity Follower.
 * 
 * @see com.scoutin.entities.Follower
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class FollowerFacade {
	// property constants
	public static final String IS_FOLLOW_PERSON = "isFollowPerson";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Follower entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Follower entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Follower entity) {
		LogUtil.log("saving Follower instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Follower entity.
	 * 
	 * @param entity
	 *            Follower entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Follower entity) {
		LogUtil.log("deleting Follower instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Follower.class, entity.getId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Follower entity and return it or a copy of it
	 * to the sender. A copy of the Follower entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Follower entity to update
	 * @return Follower the persisted Follower entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Follower update(Follower entity) {
		LogUtil.log("updating Follower instance", Level.INFO, null);
		try {
			Follower result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Follower findById(FollowerId id) {
		LogUtil.log("finding Follower instance with id: " + id, Level.INFO,
				null);
		try {
			Follower instance = entityManager.find(Follower.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Follower entity) {
		LogUtil.log("detaching Follower instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Follower entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Follower property to query
	 * @param value
	 *            the property value to match
	 * @return List<Follower> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Follower> findByProperty(String propertyName, final Object value) {
		LogUtil.log("finding Follower instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Follower model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Follower> findByIsFollowPerson(Object isFollowPerson) {
		return findByProperty(IS_FOLLOW_PERSON, isFollowPerson);
	}

	/**
	 * Find all Follower entities.
	 * 
	 * @return List<Follower> all Follower entities
	 */
	@SuppressWarnings("unchecked")
	public List<Follower> findAll() {
		LogUtil.log("finding all Follower instances", Level.INFO, null);
		try {
			final String queryString = "select model from Follower model";
			Query query = entityManager.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}