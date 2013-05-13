package com.scoutin.logic;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.scoutin.daos.AccountDao;
import com.scoutin.daos.AccountStatDao;
import com.scoutin.daos.CardDao;
import com.scoutin.daos.RecommendationDao;
import com.scoutin.entities.Card;
import com.scoutin.entities.Recommendation;

@Stateless
public class RecommendService {
	
	@EJB
	private AccountDao accountDao;
	@EJB
	private CardDao cardDao;
	@EJB
	private RecommendationDao recommendationDao;
	@EJB
	private AccountStatDao accountStatDao;
	
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
			recommendationDao.flush();
			accountStatDao.increaseUnviewRecmdCount(accountIds[i], 1);			
			/*if(++number % batchSize == 0){
				
				recommendationDao.clear();
			}*/
		}
		
		if(clusterIds.length > 0){
			List<Integer> results = accountDao.getAccountIdsInCluster(clusterIds, accountId);
			Iterator<Integer> iter = results.iterator();
			while(iter.hasNext()){
				Integer recmdedAccountId = iter.next();
				recommendation = new Recommendation();
				recommendation.setAccount(accountDao.getReference(recmdedAccountId));
				recommendation.setCard(cardDao.getReference(cardId));
				recommendationDao.save(recommendation);
				recommendationDao.flush();
				accountStatDao.increaseUnviewRecmdCount(recmdedAccountId, 1);
				/*if(++number % batchSize == 0){
					recommendationDao.flush();
					recommendationDao.clear();
				}*/
			}
			
		}
		
	}
	
	/*
	 * @see com.scoutin.logic.RecommendBeanRemote#getNewsCount(Integer accountId)
	 */
	public long getNewsCount(Integer accountId) {
		// TODO Auto-generated method stub
		return recommendationDao.getNewsCount(accountId);
	}
	
	/*
	 * @see com.scoutin.logic.RecommendBeanRemote#getNews(Integer accountId, String date, boolean forward,int limit)
	 */
	public List<Card> getNews(Integer accountId, long date, boolean forward,
			int limit) {
		return recommendationDao.getNews(accountId, date, forward, limit);
	}
}
