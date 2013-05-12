package com.scoutin.logic;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.scoutin.daos.AccountDao;
import com.scoutin.daos.CardDao;
import com.scoutin.daos.RecommendationDao;
import com.scoutin.entities.Recommendation;

@Stateless
public class RecommendService extends RecommendBean {
	
	@EJB
	private AccountDao accountDao;
	@EJB
	private CardDao cardDao;
	@EJB
	private RecommendationDao recommendationDao;
	
	private static final int batchSize = 100;
	
	/*
	 * @see com.scoutin.logic.RecommendationBeanRemote#recommendCard(Integer accountId, Long cardId, Integer[] accountIds, Long[] clusterIds)
	 */
	public void recommendCard(Integer accountId, Long cardId, Integer[] accountIds, Long[] clusterIds) {			
		
		if (cardDao.cardBelongToAccount(cardId, accountId) == false) {
			throw new IllegalArgumentException(
					"accountId and cardId don't match or not all exist");
		}
		
		Recommendation recommendation = null;
		int number = 0;
		for(int i=0; i<accountIds.length;++i){
			recommendation = new Recommendation();
			recommendation.setAccount(accountDao.getReference(accountIds[i]));
			recommendation.setCard(cardDao.getReference(cardId));
			recommendationDao.save(recommendation);
			if(++number % batchSize == 0){
				recommendationDao.flush();
				recommendationDao.clear();
			}
		}
		
		if(clusterIds.length > 0){
			List<Integer> results = accountDao.getAccountIdsInCluster(clusterIds, accountId);
			Iterator<Integer> iter = results.iterator();
			while(iter.hasNext()){
				recommendation = new Recommendation();
				recommendation.setAccount(accountDao.getReference(iter.next()));
				recommendation.setCard(cardDao.getReference(cardId));
				recommendationDao.save(recommendation);
				if(++number % batchSize == 0){
					recommendationDao.flush();
					recommendationDao.clear();
				}
			}
			
		}
		
	}
}
