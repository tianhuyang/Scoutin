package com.scoutin.facades;

import com.scoutin.entities.Notification;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Notification.
 * 
 * @see com.scoutin.entities.Notification
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class NotificationFacade {
	// property constants

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Notification entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param notification
	 *            Notification entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Notification notification) {
		LogUtil.log("saving Notification instance", Level.INFO, null);
		try {
			entityManager.persist(notification);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Notification entity.
	 * 
	 * @param notification
	 *            Notification entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Notification notification) {
		LogUtil.log("deleting Notification instance", Level.INFO, null);
		try {
			notification = entityManager.getReference(Notification.class,
					notification.getNotificationId());
			entityManager.remove(notification);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Notification entity and return it or a copy of
	 * it to the sender. A copy of the Notification entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param notification
	 *            Notification entity to update
	 * @return Notification the persisted Notification entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Notification update(Notification notification) {
		LogUtil.log("updating Notification instance", Level.INFO, null);
		try {
			Notification result = entityManager.merge(notification);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Notification findById(Long notificationId) {
		LogUtil.log("finding Notification instance with id: " + notificationId,
				Level.INFO, null);
		try {
			Notification instance = entityManager.find(Notification.class,
					notificationId);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Notification getReference(Long notificationId) {
		LogUtil.log("getReferencing Notification instance with id: "
				+ notificationId, Level.INFO, null);
		try {
			Notification instance = entityManager.getReference(
					Notification.class, notificationId);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Notification notification) {
		LogUtil.log("detaching Notification instance", Level.INFO, null);
		try {
			entityManager.detach(notification);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(Notification notification) {
		LogUtil.log("refreshing Notification instance", Level.INFO, null);
		try {
			entityManager.refresh(notification);
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

	public void remove(Notification notification) {
		LogUtil.log("removing Notification instance", Level.INFO, null);
		try {
			entityManager.remove(notification);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flush Notification instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByNotificationIdJPQL = "delete from Notification a where a.notificationId in (?1)";

	public void removeByNotificationId(Long notificationId) {
		LogUtil.log("removeByNotificationId", Level.INFO, null);
		try {
			Query query = entityManager.createQuery(removeByNotificationIdJPQL);
			query.setParameter(1, notificationId);
			query.executeUpdate();
			LogUtil.log("removeByNotificationId successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("removeByNotificationId failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Notification entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Notification property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Notification> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Notification> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Notification instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Notification model where model."
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
	 * Find all Notification entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Notification> all Notification entities
	 */
	@SuppressWarnings("unchecked")
	public List<Notification> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Notification instances", Level.INFO, null);
		try {
			final String queryString = "select model from Notification model";
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