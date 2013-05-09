package com.scoutin.facades;

import com.scoutin.entities.Album;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	 * @param album
	 *            Album entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Album album) {
		LogUtil.log("saving Album instance", Level.INFO, null);
		try {
			entityManager.persist(album);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Album entity.
	 * 
	 * @param album
	 *            Album entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Album album) {
		LogUtil.log("deleting Album instance", Level.INFO, null);
		try {
			album = entityManager.getReference(Album.class, album.getAlbumId());
			entityManager.remove(album);
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
	 * @param album
	 *            Album entity to update
	 * @return Album the persisted Album entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Album update(Album album) {
		LogUtil.log("updating Album instance", Level.INFO, null);
		try {
			Album result = entityManager.merge(album);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Album findById(Long albumId) {
		LogUtil.log("finding Album instance with id: " + albumId, Level.INFO,
				null);
		try {
			Album instance = entityManager.find(Album.class, albumId);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Album getReference(Long albumId) {
		LogUtil.log("getReferencing Album instance with id: " + albumId,
				Level.INFO, null);
		try {
			Album instance = entityManager.getReference(Album.class, albumId);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Album album) {
		LogUtil.log("detaching Album instance", Level.INFO, null);
		try {
			entityManager.detach(album);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(Album album) {
		LogUtil.log("refreshing Album instance", Level.INFO, null);
		try {
			entityManager.refresh(album);
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

	public void remove(Album album) {
		LogUtil.log("removing Album instance", Level.INFO, null);
		try {
			entityManager.remove(album);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flush Album instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByAlbumIdJPQL = "delete from Album a where a.albumId in (?1)";

	public void removeByAlbumId(Long albumId) {
		LogUtil.log("removeByAlbumId", Level.INFO, null);
		try {
			Query query = entityManager.createQuery(removeByAlbumIdJPQL);
			query.setParameter(1, albumId);
			query.executeUpdate();
			LogUtil.log("removeByAlbumId successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("removeByAlbumId failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String accountIdJPQL = "select a.account.accountId from Album a where a.albumId = :albumId";

	public java.lang.Integer getAccountId(java.lang.Long albumId) {
		LogUtil.log("getAccountIdId with albumId" + albumId, Level.INFO, null);
		java.lang.Integer accountId;
		try {
			Query query = entityManager.createQuery(accountIdJPQL);
			query.setParameter("albumId", albumId);
			accountId = (java.lang.Integer) query.getSingleResult();
			LogUtil.log("getAccountIdId successful", Level.INFO, null);
			return accountId;
		} catch (RuntimeException re) {
			LogUtil.log("getAccountIdId failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String increaseFollowCountJPQL = "update ALBUM a set a.followCount = a.followCount + :count where a.albumId in (:albumId)";

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
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Album> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Album> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		LogUtil.log("finding Album instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Album model where model."
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

	public List<Album> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}

	public List<Album> findByCoverPath(Object coverPath,
			int... rowStartIdxAndCount) {
		return findByProperty(COVER_PATH, coverPath, rowStartIdxAndCount);
	}

	public List<Album> findByFollowCount(Object followCount,
			int... rowStartIdxAndCount) {
		return findByProperty(FOLLOW_COUNT, followCount, rowStartIdxAndCount);
	}

	/**
	 * Find all Album entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Album> all Album entities
	 */
	@SuppressWarnings("unchecked")
	public List<Album> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Album instances", Level.INFO, null);
		try {
			final String queryString = "select model from Album model";
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