package com.scoutin.facades;

import com.scoutin.entities.BlockedAlbum;
import com.scoutin.entities.BlockedAlbumId;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity BlockedAlbum.
 * 
 * @see com.scoutin.entities.BlockedAlbum
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class BlockedAlbumFacade {
	// property constants

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved BlockedAlbum entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param blockedAlbum
	 *            BlockedAlbum entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(BlockedAlbum blockedAlbum) {
		LogUtil.log("saving BlockedAlbum instance", Level.INFO, null);
		try {
			entityManager.persist(blockedAlbum);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent BlockedAlbum entity.
	 * 
	 * @param blockedAlbum
	 *            BlockedAlbum entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(BlockedAlbum blockedAlbum) {
		LogUtil.log("deleting BlockedAlbum instance", Level.INFO, null);
		try {
			blockedAlbum = entityManager.getReference(BlockedAlbum.class,
					blockedAlbum.getId());
			entityManager.remove(blockedAlbum);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved BlockedAlbum entity and return it or a copy of
	 * it to the sender. A copy of the BlockedAlbum entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param blockedAlbum
	 *            BlockedAlbum entity to update
	 * @return BlockedAlbum the persisted BlockedAlbum entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public BlockedAlbum update(BlockedAlbum blockedAlbum) {
		LogUtil.log("updating BlockedAlbum instance", Level.INFO, null);
		try {
			BlockedAlbum result = entityManager.merge(blockedAlbum);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public BlockedAlbum findById(BlockedAlbumId id) {
		LogUtil.log("finding BlockedAlbum instance with id: " + id, Level.INFO,
				null);
		try {
			BlockedAlbum instance = entityManager.find(BlockedAlbum.class, id);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public BlockedAlbum getReference(BlockedAlbumId id) {
		LogUtil.log("getReferencing BlockedAlbum instance with id: " + id,
				Level.INFO, null);
		try {
			BlockedAlbum instance = entityManager.getReference(
					BlockedAlbum.class, id);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(BlockedAlbum blockedAlbum) {
		LogUtil.log("detaching BlockedAlbum instance", Level.INFO, null);
		try {
			entityManager.detach(blockedAlbum);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(BlockedAlbum blockedAlbum) {
		LogUtil.log("refreshing BlockedAlbum instance", Level.INFO, null);
		try {
			entityManager.refresh(blockedAlbum);
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

	public void remove(BlockedAlbum blockedAlbum) {
		LogUtil.log("removing BlockedAlbum instance", Level.INFO, null);
		try {
			entityManager.remove(blockedAlbum);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flushing BlockedAlbum instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void clear() {
		LogUtil.log("clearing BlockedAlbum instance", Level.INFO, null);
		try {
			entityManager.clear();
			LogUtil.log("clear successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("clear failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByIdJPQL = "delete from BlockedAlbum a where a.id in (?1)";

	public int removeById(BlockedAlbumId id) {
		LogUtil.log("removeById", Level.INFO, null);
		int ret = 0;
		try {
			Query query = entityManager.createQuery(removeByIdJPQL);
			query.setParameter(1, id);
			ret = query.executeUpdate();
			LogUtil.log("removeById successful", Level.INFO, null);
			return ret;
		} catch (RuntimeException re) {
			LogUtil.log("removeById failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all BlockedAlbum entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the BlockedAlbum property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<BlockedAlbum> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<BlockedAlbum> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding BlockedAlbum instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from BlockedAlbum model where model."
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

	/**
	 * Find all BlockedAlbum entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<BlockedAlbum> all BlockedAlbum entities
	 */
	@SuppressWarnings("unchecked")
	public List<BlockedAlbum> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all BlockedAlbum instances", Level.INFO, null);
		try {
			final String queryString = "select model from BlockedAlbum model";
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