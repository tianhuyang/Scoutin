package com.scoutin.logic;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.scoutin.application.exception.ApplicationException;
import com.scoutin.application.interfaces.RecommendBeanRemote;
import com.scoutin.entities.Card;
import com.scoutin.entities.Recommendation;

@Stateless
public class RecommendBean implements RecommendBeanRemote {
	
	@EJB
	private RecommendService recommendService;
	
	/*
	 * @see com.scoutin.logic.RecommendBeanRemote#recommendCard(Integer accountId, Long cardId, Integer[] accountIds, Long[] clusterIds)
	 */
	@Override
	public void recommendCard(Integer accountId, Long cardId, Integer[] accountIds, Long[] clusterIds) {
		try {
			recommendService.recommendCard(accountId, cardId, accountIds, clusterIds);
		} catch (Throwable t) {
			throw new ApplicationException(t.getMessage());
		}		
	}

	/*
	 * @see com.scoutin.logic.RecommendBeanRemote#getNewsCount(Integer accountId)
	 */
	@Override
	public long getNewsCount(Integer accountId) {
		return recommendService.getNewsCount(accountId);
	}

	/*
	 * @see com.scoutin.logic.RecommendBeanRemote#getNews(Integer accountId, long date, boolean forward,int limit)
	 */
	@Override
	public List<Card> getNews(Integer accountId, long date, boolean forward,
			int limit) {
		if(limit > MaxNewsLimit)
			limit = MaxNewsLimit;
		return recommendService.getNews(accountId, date, forward, limit);
	}

	@Override
	public void viewedNews(Integer accountId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getRecommendationsCount(Integer accountId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Recommendation> getRecommendations(Integer accountId, long date,
			boolean forward, Boolean viewed, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void viewedRecommenations(Integer accountId,
			Long[] recommendationIds, boolean viewed) {
		// TODO Auto-generated method stub
		
	}
}
