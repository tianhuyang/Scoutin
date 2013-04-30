package com.scoutin.facades;

import com.scoutin.entities.Cardbody;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Cardbody.
 * 
 * @see com.scoutin.entities.Cardbody
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class CardbodyFacade {
	// property constants
	public static final String VERSION = "version";
	public static final String RATING = "rating";
	public static final String COMMENTS_COUNT = "commentsCount";
	public static final String REPOSTS_COUNT = "repostsCount";
	public static final String LIKES_COUNT = "likesCount";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String ADDRESS = "address";
	public static final String URL = "url";
	public static final String TITLE = "title";
	public static final String RATING_COUNT = "ratingCount";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Cardbody entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Cardbody entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Cardbody entity) {
		LogUtil.log("saving Cardbody instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Cardbody entity.
	 * 
	 * @param entity
	 *            Cardbody entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Cardbody entity) {
		LogUtil.log("deleting Cardbody instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Cardbody.class,
					entity.getCardbodyId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Cardbody entity and return it or a copy of it
	 * to the sender. A copy of the Cardbody entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Cardbody entity to update
	 * @return Cardbody the persisted Cardbody entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Cardbody update(Cardbody entity) {
		LogUtil.log("updating Cardbody instance", Level.INFO, null);
		try {
			Cardbody result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Cardbody findById(Long id) {
		LogUtil.log("finding Cardbody instance with id: " + id, Level.INFO,
				null);
		try {
			Cardbody instance = entityManager.find(Cardbody.class, id);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Cardbody getReference(Long id) {
		LogUtil.log("getReferencing Cardbody instance with id: " + id,
				Level.INFO, null);
		try {
			Cardbody instance = entityManager.getReference(Cardbody.class, id);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Cardbody entity) {
		LogUtil.log("detaching Cardbody instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(Cardbody entity) {
		LogUtil.log("refreshing Cardbody instance", Level.INFO, null);
		try {
			entityManager.refresh(entity);
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

	public void remove(Cardbody entity) {
		LogUtil.log("removing Cardbody instance", Level.INFO, null);
		try {
			entityManager.remove(entity);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flush Cardbody instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseCommentsCountJPQL = "update Cardbody a set a.commentsCount = a.commentsCount + :count where a.cardbodyId in (:cardbodyId)";

	public void increaseCommentsCount(java.lang.Long cardbodyId, int count) {
		LogUtil.log("increaseRatingCount with cardbodyId:" + cardbodyId,
				Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseCommentsCountJPQL);
			query.setParameter("cardbodyId", cardbodyId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseRatingCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseRatingCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseRepostsCountJPQL = "update Cardbody a set a.repostsCount = a.repostsCount + :count where a.cardbodyId in (:cardbodyId)";

	public void increaseRepostsCount(java.lang.Long cardbodyId, int count) {
		LogUtil.log("increaseRatingCount with cardbodyId:" + cardbodyId,
				Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseRepostsCountJPQL);
			query.setParameter("cardbodyId", cardbodyId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseRatingCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseRatingCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseLikesCountJPQL = "update Cardbody a set a.likesCount = a.likesCount + :count where a.cardbodyId in (:cardbodyId)";

	public void increaseLikesCount(java.lang.Long cardbodyId, int count) {
		LogUtil.log("increaseRatingCount with cardbodyId:" + cardbodyId,
				Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseLikesCountJPQL);
			query.setParameter("cardbodyId", cardbodyId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseRatingCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseRatingCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseRatingCountJPQL = "update Cardbody a set a.ratingCount = a.ratingCount + :count where a.cardbodyId in (:cardbodyId)";

	public void increaseRatingCount(java.lang.Long cardbodyId, int count) {
		LogUtil.log("increaseRatingCount with cardbodyId:" + cardbodyId,
				Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseRatingCountJPQL);
			query.setParameter("cardbodyId", cardbodyId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseRatingCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseRatingCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Cardbody entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Cardbody property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Cardbody> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Cardbody> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Cardbody instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Cardbody model where model."
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

	public List<Cardbody> findByVersion(Object version,
			int... rowStartIdxAndCount) {
		return findByProperty(VERSION, version, rowStartIdxAndCount);
	}

	public List<Cardbody> findByRating(Object rating,
			int... rowStartIdxAndCount) {
		return findByProperty(RATING, rating, rowStartIdxAndCount);
	}

	public List<Cardbody> findByCommentsCount(Object commentsCount,
			int... rowStartIdxAndCount) {
		return findByProperty(COMMENTS_COUNT, commentsCount,
				rowStartIdxAndCount);
	}

	public List<Cardbody> findByRepostsCount(Object repostsCount,
			int... rowStartIdxAndCount) {
		return findByProperty(REPOSTS_COUNT, repostsCount, rowStartIdxAndCount);
	}

	public List<Cardbody> findByLikesCount(Object likesCount,
			int... rowStartIdxAndCount) {
		return findByProperty(LIKES_COUNT, likesCount, rowStartIdxAndCount);
	}

	public List<Cardbody> findByLatitude(Object latitude,
			int... rowStartIdxAndCount) {
		return findByProperty(LATITUDE, latitude, rowStartIdxAndCount);
	}

	public List<Cardbody> findByLongitude(Object longitude,
			int... rowStartIdxAndCount) {
		return findByProperty(LONGITUDE, longitude, rowStartIdxAndCount);
	}

	public List<Cardbody> findByAddress(Object address,
			int... rowStartIdxAndCount) {
		return findByProperty(ADDRESS, address, rowStartIdxAndCount);
	}

	public List<Cardbody> findByUrl(Object url, int... rowStartIdxAndCount) {
		return findByProperty(URL, url, rowStartIdxAndCount);
	}

	public List<Cardbody> findByTitle(Object title, int... rowStartIdxAndCount) {
		return findByProperty(TITLE, title, rowStartIdxAndCount);
	}

	public List<Cardbody> findByRatingCount(Object ratingCount,
			int... rowStartIdxAndCount) {
		return findByProperty(RATING_COUNT, ratingCount, rowStartIdxAndCount);
	}

	/**
	 * Find all Cardbody entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Cardbody> all Cardbody entities
	 */
	@SuppressWarnings("unchecked")
	public List<Cardbody> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Cardbody instances", Level.INFO, null);
		try {
			final String queryString = "select model from Cardbody model";
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