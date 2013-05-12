package com.scoutin.facades;

import com.scoutin.entities.Category;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	 * @param category
	 *            Category entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Category category) {
		LogUtil.log("saving Category instance", Level.INFO, null);
		try {
			entityManager.persist(category);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Category entity.
	 * 
	 * @param category
	 *            Category entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Category category) {
		LogUtil.log("deleting Category instance", Level.INFO, null);
		try {
			category = entityManager.getReference(Category.class,
					category.getCategoryId());
			entityManager.remove(category);
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
	 * @param category
	 *            Category entity to update
	 * @return Category the persisted Category entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Category update(Category category) {
		LogUtil.log("updating Category instance", Level.INFO, null);
		try {
			Category result = entityManager.merge(category);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Category findById(Short categoryId) {
		LogUtil.log("finding Category instance with id: " + categoryId,
				Level.INFO, null);
		try {
			Category instance = entityManager.find(Category.class, categoryId);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Category getReference(Short categoryId) {
		LogUtil.log("getReferencing Category instance with id: " + categoryId,
				Level.INFO, null);
		try {
			Category instance = entityManager.getReference(Category.class,
					categoryId);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Category category) {
		LogUtil.log("detaching Category instance", Level.INFO, null);
		try {
			entityManager.detach(category);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(Category category) {
		LogUtil.log("refreshing Category instance", Level.INFO, null);
		try {
			entityManager.refresh(category);
			LogUtil.log("refresh successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("refresh failed", Level.SEVERE, re);
			throw re;
		}
	}

	/*
	 * for persistent instance, remove directly
	 * 
	 * @see delete
	 */

	public void remove(Category category) {
		LogUtil.log("removing Category instance", Level.INFO, null);
		try {
			entityManager.remove(category);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flushing Category instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void clear() {
		LogUtil.log("clearing Category instance", Level.INFO, null);
		try {
			entityManager.clear();
			LogUtil.log("clear successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("clear failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByCategoryIdJPQL = "delete from Category a where a.categoryId in (?1)";

	public int removeByCategoryId(Short categoryId) {
		LogUtil.log("removeByCategoryId", Level.INFO, null);
		int ret = 0;
		try {
			Query query = entityManager.createQuery(removeByCategoryIdJPQL);
			query.setParameter(1, categoryId);
			ret = query.executeUpdate();
			LogUtil.log("removeByCategoryId successful", Level.INFO, null);
			return ret;
		} catch (RuntimeException re) {
			LogUtil.log("removeByCategoryId failed", Level.SEVERE, re);
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
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Category> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Category> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Category instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Category model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Category> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}

	/**
	 * Find all Category entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Category> all Category entities
	 */
	@SuppressWarnings("unchecked")
	public List<Category> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Category instances", Level.INFO, null);
		try {
			final String queryString = "select model from Category model";
			Query query = entityManager.createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}