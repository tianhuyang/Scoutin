package com.scoutin.daos;

import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.scoutin.facades.LogUtil;
import com.scoutin.facades.RecommendationFacade;

@Stateless
public class RecommendationDao extends RecommendationFacade {

	private static final String recommendCardJPQL = "insert into Recommendation r (r.cardId, r.accountId, r.createdTime) values(select :cardId, ac.id.accountId, CURRENT_TIMESTAMP from AccountCluster ac where ac.id.clusterId in (:clusterIds) union select :cardId,(:accountIds),CURRENT_TIMESTAMP)";
	
	public void recommendCard(Long cardId, Integer[] accountIds, Long[] clusterIds) {
		LogUtil.log("recommendCarding", Level.INFO, null);
		try {
			Query query = entityManager.createQuery(recommendCardJPQL);
			query.setParameter("cardId", cardId);
			query.setParameter("clusterIds", clusterIds);
			query.setParameter("accountIds", accountIds);
			query.getSingleResult();
			LogUtil.log("recommendCard successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("recommendCard failed", Level.SEVERE, re);
			throw re;
		}
	}
}
