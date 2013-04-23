package com.scoutin.facades;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.scoutin.entities.Comment;

/**
 * Facade for entity Comment.
 * 
 * @see com.scoutin.entities.Comment
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class CommentFacade {
	// property constants
	public static final String CONTENT = "content";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Comment entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Comment entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Comment entity) {
		LogUtil.log("saving Comment instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Comment entity.
	 * 
	 * @param entity
	 *            Comment entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Comment entity) {
		LogUtil.log("deleting Comment instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Comment.class,
					entity.getCommentId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Comment entity and return it or a copy of it
	 * to the sender. A copy of the Comment entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Comment entity to update
	 * @return Comment the persisted Comment entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Comment update(Comment entity) {
		LogUtil.log("updating Comment instance", Level.INFO, null);
		try {
			Comment result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Comment findById(Long id) {
		LogUtil.log("finding Comment instance with id: " + id, Level.INFO, null);
		try {
			Comment instance = entityManager.find(Comment.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Comment entity) {
		LogUtil.log("detaching Comment instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Comment entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Comment property to query
	 * @param value
	 *            the property value to match
	 * @return List<Comment> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> findByProperty(String propertyName, final Object value) {
		LogUtil.log("finding Comment instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Comment model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Comment> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	/**
	 * Find all Comment entities.
	 * 
	 * @return List<Comment> all Comment entities
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> findAll() {
		LogUtil.log("finding all Comment instances", Level.INFO, null);
		try {
			final String queryString = "select model from Comment model";
			Query query = entityManager.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}