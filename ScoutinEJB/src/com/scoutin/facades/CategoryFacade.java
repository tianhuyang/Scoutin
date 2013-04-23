package com.scoutin.facades;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.scoutin.entities.Category;

/**
 * Facade for entity Category.
 * 
 * @see com.scoutin.entities.Category
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class CategoryFacade {
	// property constants
	public static final String NAME = "name";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Category entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Category entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Category entity) {
		LogUtil.log("saving Category instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Category entity.
	 * 
	 * @param entity
	 *            Category entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Category entity) {
		LogUtil.log("deleting Category instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Category.class,
					entity.getCategoryId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Category entity and return it or a copy of it
	 * to the sender. A copy of the Category entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Category entity to update
	 * @return Category the persisted Category entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Category update(Category entity) {
		LogUtil.log("updating Category instance", Level.INFO, null);
		try {
			Category result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Category findById(Short id) {
		LogUtil.log("finding Category instance with id: " + id, Level.INFO,
				null);
		try {
			Category instance = entityManager.find(Category.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Category entity) {
		LogUtil.log("detaching Category instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Category entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Category property to query
	 * @param value
	 *            the property value to match
	 * @return List<Category> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Category> findByProperty(String propertyName, final Object value) {
		LogUtil.log("finding Category instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Category model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Category> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/**
	 * Find all Category entities.
	 * 
	 * @return List<Category> all Category entities
	 */
	@SuppressWarnings("unchecked")
	public List<Category> findAll() {
		LogUtil.log("finding all Category instances", Level.INFO, null);
		try {
			final String queryString = "select model from Category model";
			Query query = entityManager.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}