package com.scoutin.facades;

import com.scoutin.entities.Message;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Message.
 * 
 * @see com.scoutin.entities.Message
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class MessageFacade {
	// property constants

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Message entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param message
	 *            Message entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Message message) {
		LogUtil.log("saving Message instance", Level.INFO, null);
		try {
			entityManager.persist(message);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Message entity.
	 * 
	 * @param message
	 *            Message entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Message message) {
		LogUtil.log("deleting Message instance", Level.INFO, null);
		try {
			message = entityManager.getReference(Message.class,
					message.getMessageId());
			entityManager.remove(message);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Message entity and return it or a copy of it
	 * to the sender. A copy of the Message entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param message
	 *            Message entity to update
	 * @return Message the persisted Message entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Message update(Message message) {
		LogUtil.log("updating Message instance", Level.INFO, null);
		try {
			Message result = entityManager.merge(message);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Message findById(Long messageId) {
		LogUtil.log("finding Message instance with id: " + messageId,
				Level.INFO, null);
		try {
			Message instance = entityManager.find(Message.class, messageId);
			LogUtil.log("find successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Message getReference(Long messageId) {
		LogUtil.log("getReferencing Message instance with id: " + messageId,
				Level.INFO, null);
		try {
			Message instance = entityManager.getReference(Message.class,
					messageId);
			LogUtil.log("getReference successful", Level.INFO, null);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("getReference failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void detach(Message message) {
		LogUtil.log("detaching Message instance", Level.INFO, null);
		try {
			entityManager.detach(message);
			LogUtil.log("detach successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("detach failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void refresh(Message message) {
		LogUtil.log("refreshing Message instance", Level.INFO, null);
		try {
			entityManager.refresh(message);
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

	public void remove(Message message) {
		LogUtil.log("removing Message instance", Level.INFO, null);
		try {
			entityManager.remove(message);
			LogUtil.log("remove successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("remove failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void flush() {
		LogUtil.log("flushing Message instance", Level.INFO, null);
		try {
			entityManager.flush();
			LogUtil.log("flush successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("flush failed", Level.SEVERE, re);
			throw re;
		}
	}

	public void clear() {
		LogUtil.log("clearing Message instance", Level.INFO, null);
		try {
			entityManager.clear();
			LogUtil.log("clear successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("clear failed", Level.SEVERE, re);
			throw re;
		}
	}

	private static final String removeByMessageIdJPQL = "delete from Message a where a.messageId in (?1)";

	public int removeByMessageId(Long messageId) {
		LogUtil.log("removeByMessageId", Level.INFO, null);
		int ret = 0;
		try {
			Query query = entityManager.createQuery(removeByMessageIdJPQL);
			query.setParameter(1, messageId);
			ret = query.executeUpdate();
			LogUtil.log("removeByMessageId successful", Level.INFO, null);
			return ret;
		} catch (RuntimeException re) {
			LogUtil.log("removeByMessageId failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Message entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Message property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Message> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Message> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Message instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Message model where model."
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
	 * Find all Message entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Message> all Message entities
	 */
	@SuppressWarnings("unchecked")
	public List<Message> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Message instances", Level.INFO, null);
		try {
			final String queryString = "select model from Message model";
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