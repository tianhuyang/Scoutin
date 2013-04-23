package com.scoutin.facades;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.scoutin.entities.Album;

/**
 * Facade for entity Album.
 * 
 * @see com.scoutin.entities.Album
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class AlbumFacade {
	// property constants
	public static final String NAME = "name";
	public static final String COVER_PATH = "coverPath";
	public static final String FOLLOW_COUNT = "followCount";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Album entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Album entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Album entity) {
		LogUtil.log("saving Album instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Album entity.
	 * 
	 * @param entity
	 *            Album entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Album entity) {
		LogUtil.log("deleting Album instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Album.class,
					entity.getAlbumId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Album entity and return it or a copy of it to
	 * the sender. A copy of the Album entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity.
	 * 
	 * @param entity
	 *            Album entity to update
	 * @return Album the persisted Album entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Album update(Album entity) {
		LogUtil.log("updating Album instance", Level.INFO, null);
		try {
			Album result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Album findById(Long id) {
		LogUtil.log("finding Album instance with id: " + id, Level.INFO, null);
		try {
			Album instance = entityManager.find(Album.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Album entity) {
		LogUtil.log("detaching Album instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	private final String increaseFollowCountJPQL = "update Album a set a.followCount = a.followCount + :count where a.albumId =:albumId";

	public void increaseFollowCount(java.lang.Long albumId, int count) {
		LogUtil.log("increaseFollowCount with albumId:" + albumId, Level.INFO,
				null);
		try {
			Query query = entityManager.createQuery(increaseFollowCountJPQL);
			query.setParameter("albumId", albumId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseFollowCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseFollowCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Album entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Album property to query
	 * @param value
	 *            the property value to match
	 * @return List<Album> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Album> findByProperty(String propertyName, final Object value) {
		LogUtil.log("finding Album instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Album model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Album> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Album> findByCoverPath(Object coverPath) {
		return findByProperty(COVER_PATH, coverPath);
	}

	public List<Album> findByFollowCount(Object followCount) {
		return findByProperty(FOLLOW_COUNT, followCount);
	}

	/**
	 * Find all Album entities.
	 * 
	 * @return List<Album> all Album entities
	 */
	@SuppressWarnings("unchecked")
	public List<Album> findAll() {
		LogUtil.log("finding all Album instances", Level.INFO, null);
		try {
			final String queryString = "select model from Album model";
			Query query = entityManager.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}