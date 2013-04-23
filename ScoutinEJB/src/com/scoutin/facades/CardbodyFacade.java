package com.scoutin.facades;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.scoutin.entities.Cardbody;

/**
 * Facade for entity Cardbody.
 * 
 * @see com.scoutin.entities.Cardbody
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class CardbodyFacade {
	// property constants
	public static final String RATING = "rating";
	public static final String COMMENTS_COUNT = "commentsCount";
	public static final String REPOSTS_COUNT = "repostsCount";
	public static final String LIKES_COUNT = "likesCount";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String ADDRESS = "address";
	public static final String URL = "url";
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
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Cardbody entity) {
		LogUtil.log("detaching Cardbody instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	private final String increaseCommentsCountJPQL = "update Cardbody a set a.commentsCount = a.commentsCount + :count where a.cardbodyId =:cardbodyId";

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

	private final String increaseRepostsCountJPQL = "update Cardbody a set a.repostsCount = a.repostsCount + :count where a.cardbodyId =:cardbodyId";

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

	private final String increaseLikesCountJPQL = "update Cardbody a set a.likesCount = a.likesCount + :count where a.cardbodyId =:cardbodyId";

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

	private final String increaseRatingCountJPQL = "update Cardbody a set a.ratingCount = a.ratingCount + :count where a.cardbodyId =:cardbodyId";

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
	 * @return List<Cardbody> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Cardbody> findByProperty(String propertyName, final Object value) {
		LogUtil.log("finding Cardbody instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Cardbody model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Cardbody> findByRating(Object rating) {
		return findByProperty(RATING, rating);
	}

	public List<Cardbody> findByCommentsCount(Object commentsCount) {
		return findByProperty(COMMENTS_COUNT, commentsCount);
	}

	public List<Cardbody> findByRepostsCount(Object repostsCount) {
		return findByProperty(REPOSTS_COUNT, repostsCount);
	}

	public List<Cardbody> findByLikesCount(Object likesCount) {
		return findByProperty(LIKES_COUNT, likesCount);
	}

	public List<Cardbody> findByLatitude(Object latitude) {
		return findByProperty(LATITUDE, latitude);
	}

	public List<Cardbody> findByLongitude(Object longitude) {
		return findByProperty(LONGITUDE, longitude);
	}

	public List<Cardbody> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Cardbody> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<Cardbody> findByRatingCount(Object ratingCount) {
		return findByProperty(RATING_COUNT, ratingCount);
	}

	/**
	 * Find all Cardbody entities.
	 * 
	 * @return List<Cardbody> all Cardbody entities
	 */
	@SuppressWarnings("unchecked")
	public List<Cardbody> findAll() {
		LogUtil.log("finding all Cardbody instances", Level.INFO, null);
		try {
			final String queryString = "select model from Cardbody model";
			Query query = entityManager.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}