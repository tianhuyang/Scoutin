package com.scoutin.daos;

import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.Query;
import com.scoutin.facades.CardBodyFacade;
import com.scoutin.facades.LogUtil;
@Stateless
public class CardBodyDao extends CardBodyFacade {

	public CardBodyDao() {
		// TODO Auto-generated constructor stub
	}
	
    private static final String cardBodyBelongToAccountHql = "select count(cb) from CardBody cb where cb.account.accountId = :accountId and cb.cardBodyId = :cardBodyId";
 	
	public boolean cardBodyBelongToAccount(long cardBodyId, int accountId) {
		LogUtil.log("cardBodyBelongToAccounting", Level.INFO, null);
		try {
			/*CriteriaQuery<Tuple> cq = entityManager.getCriteriaBuilder().createTupleQuery();
			Root<Cardbody> root = cq.from(Cardbody.class);
			cq.
			cq.multiselect(root.get("cardBodyId"), root.get("accountId"));
			List<Tuple> tupleResult = entityManager.createQuery(cq).getResultList();
			LogUtil.log("cardBodyBelongToAccount successful", Level.INFO, null);
			return tupleResult.size() == 1;*/
			Query query = entityManager.createQuery(cardBodyBelongToAccountHql);
			query.setParameter("accountId", accountId);
			query.setParameter("cardBodyId", cardBodyId);
			long count = (Long)query.getSingleResult();
			LogUtil.log("cardBodyBelongToAccount successful", Level.INFO, null);
			return count == 1;
		} catch (RuntimeException re) {
			LogUtil.log("cardBodyBelongToAccount failed", Level.SEVERE, re);
			throw re;
		}	
	}
	
	//private final String increaseEndorsesCountByCardIdJPQL = "update Cardbody  a set a.endorsesCount = a.endorsesCount + :count where :cardId member of a.cards";
	private static final String increaseEndorsesCountByCardIdJPQL = "update Cardbody a set a.endorsesCount = a.endorsesCount + :count where exists (select b.cardId from Card b where b.cardBody.cardBodyId = a.cardBodyId and b.cardId = :cardId)";

	public void increaseEndorsesCountByCardId(java.lang.Long cardId, int count) {
		LogUtil.log("increaseEndorsesCountByCardId with cardId:" + cardId,
				Level.INFO, null);
		try {
			Query query = entityManager.createQuery(increaseEndorsesCountByCardIdJPQL); 
			query.setParameter("cardId", cardId);
			query.setParameter("count", count);
			query.executeUpdate();
			LogUtil.log("increaseEndorsesCountByCardId successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("increaseEndorsesCountByCardId failed", Level.SEVERE, re);
			throw re;
		}
	}
	
	private static final String increaseCommentsCountByCardIdJPQL = "update Cardbody a set a.commentsCount = a.commentsCount + :count where exists (select b from Card b where b.cardBody = a and b.cardId = :cardId)";

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
