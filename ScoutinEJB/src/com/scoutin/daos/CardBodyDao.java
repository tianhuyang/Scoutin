package com.scoutin.daos;

import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import com.scoutin.facades.CardbodyFacade;
import com.scoutin.facades.LogUtil;
@Stateless
public class CardBodyDao extends CardbodyFacade {

	public CardBodyDao() {
		// TODO Auto-generated constructor stub
	}
	
    private static final String cardbodyBelongToAccountHql = "select count(cb) from Cardbody cb where cb.account.accountId = :accountId and cb.cardbodyId = :cardbodyId";
 	
	public boolean cardbodyBelongToAccount(long cardbodyId, int accountId) {
		LogUtil.log("cardbodyBelongToAccounting", Level.INFO, null);
		try {
			/*CriteriaQuery<Tuple> cq = entityManager.getCriteriaBuilder().createTupleQuery();
			Root<Cardbody> root = cq.from(Cardbody.class);
			cq.
			cq.multiselect(root.get("cardbodyId"), root.get("accountId"));
			List<Tuple> tupleResult = entityManager.createQuery(cq).getResultList();
			LogUtil.log("cardbodyBelongToAccount successful", Level.INFO, null);
			return tupleResult.size() == 1;*/
			Query query = entityManager.createQuery(cardbodyBelongToAccountHql);
			query.setParameter("accountId", accountId);
			query.setParameter("cardbodyId", cardbodyId);
			long count = (Long)query.getSingleResult();
			LogUtil.log("cardbodyBelongToAccount successful", Level.INFO, null);
			return count == 1;
		} catch (RuntimeException re) {
			LogUtil.log("cardbodyBelongToAccount failed", Level.SEVERE, re);
			throw re;
		}	
	}
	
	//private final String increaseLikesCountByCardIdJPQL = "update Cardbody  a set a.likesCount = a.likesCount + :count where :cardId member of a.cards";
	private static final String increaseLikesCountByCardIdJPQL = "update Cardbody a set a.likesCount = a.likesCount + :count where exists (select b.cardId from Card b where b.cardbody.cardbodyId = a.cardbodyId and b.cardId = :cardId)";

	public void increaseLikesCountByCardId(java.lang.Long cardId, int count) {
		LogUtil.log("increaseLikesCountByCardId with cardId:" + cardId,
				Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseLikesCountByCardIdJPQL); 
			query.setParameter("cardId", cardId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseLikesCountByCardId successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseLikesCountByCardId failed", Level.SEVERE, re);
			throw re;
		}
	}
	
	private static final String increaseCommentsCountByCardIdJPQL = "update Cardbody a set a.commentsCount = a.commentsCount + :count where exists (select b from Card b where b.cardbody = a and b.cardId = :cardId)";

	public void increaseCommentsCountByCardId(java.lang.Long cardId, int count) {
		LogUtil.log("increaseCommentsCountByCardId with cardId:" + cardId,
				Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseCommentsCountByCardIdJPQL); 
			query.setParameter("cardId", cardId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseCommentsCountByCardId successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseCommentsCountByCardId failed", Level.SEVERE, re);
			throw re;
		}
	}

}
