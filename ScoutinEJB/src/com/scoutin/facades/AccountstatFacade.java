package com.scoutin.facades;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.scoutin.entities.Accountstat;

/**
 * Facade for entity Accountstat.
 * 
 * @see com.scoutin.entities.Accountstat
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class AccountstatFacade {
	// property constants
	public static final String FOLLOWING_COUNT = "followingCount";
	public static final String FOLLOWERS_COUNT = "followersCount";
	public static final String UNVIEW_RECMD_COUNT = "unviewRecmdCount";

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Accountstat entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Accountstat entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Accountstat entity) {
		LogUtil.log("saving Accountstat instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Accountstat entity.
	 * 
	 * @param entity
	 *            Accountstat entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Accountstat entity) {
		LogUtil.log("deleting Accountstat instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Accountstat.class,
					entity.getAccountId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Accountstat entity and return it or a copy of
	 * it to the sender. A copy of the Accountstat entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Accountstat entity to update
	 * @return Accountstat the persisted Accountstat entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Accountstat update(Accountstat entity) {
		LogUtil.log("updating Accountstat instance", Level.INFO, null);
		try {
			Accountstat result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Accountstat findById(Integer id) {
		LogUtil.log("finding Accountstat instance with id: " + id, Level.INFO,
				null);
		try {
			Accountstat instance = entityManager.find(Accountstat.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Accountstat entity) {
		LogUtil.log("detaching Accountstat instance", Level.INFO, null);
		try {
			entityManager.detach(entity);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	private final String increaseFollowingCountJPQL = "update Accountstat a set a.followingCount = a.followingCount + :count where a.accountId =:accountId";

	public void increaseFollowingCount(java.lang.Integer accountId, int count) {
		LogUtil.log("increaseUnviewRecmdCount with accountId:" + accountId,
				Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseFollowingCountJPQL);
			query.setParameter("accountId", accountId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseUnviewRecmdCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseUnviewRecmdCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	private final String increaseFollowersCountJPQL = "update Accountstat a set a.followersCount = a.followersCount + :count where a.accountId =:accountId";

	public void increaseFollowersCount(java.lang.Integer accountId, int count) {
		LogUtil.log("increaseUnviewRecmdCount with accountId:" + accountId,
				Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseFollowersCountJPQL);
			query.setParameter("accountId", accountId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseUnviewRecmdCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseUnviewRecmdCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	private final String increaseUnviewRecmdCountJPQL = "update Accountstat a set a.unviewRecmdCount = a.unviewRecmdCount + :count where a.accountId =:accountId";

	public void increaseUnviewRecmdCount(java.lang.Integer accountId, int count) {
		LogUtil.log("increaseUnviewRecmdCount with accountId:" + accountId,
				Level.INFO, null);
		try {
			Query query = entityManager
					.createQuery(increaseUnviewRecmdCountJPQL);
			query.setParameter("accountId", accountId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseUnviewRecmdCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseUnviewRecmdCount failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Accountstat entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Accountstat property to query
	 * @param value
	 *            the property value to match
	 * @return List<Accountstat> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Accountstat> findByProperty(String propertyName,
			final Object value) {
		LogUtil.log("finding Accountstat instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Accountstat model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Accountstat> findByFollowingCount(Object followingCount) {
		return findByProperty(FOLLOWING_COUNT, followingCount);
	}

	public List<Accountstat> findByFollowersCount(Object followersCount) {
		return findByProperty(FOLLOWERS_COUNT, followersCount);
	}

	public List<Accountstat> findByUnviewRecmdCount(Object unviewRecmdCount) {
		return findByProperty(UNVIEW_RECMD_COUNT, unviewRecmdCount);
	}

	/**
	 * Find all Accountstat entities.
	 * 
	 * @return List<Accountstat> all Accountstat entities
	 */
	@SuppressWarnings("unchecked")
	public List<Accountstat> findAll() {
		LogUtil.log("finding all Accountstat instances", Level.INFO, null);
		try {
			final String queryString = "select model from Accountstat model";
			Query query = entityManager.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}