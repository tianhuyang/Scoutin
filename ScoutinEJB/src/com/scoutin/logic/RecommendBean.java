package com.scoutin.logic;

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
	 * @see com.scoutin.logic.RecommendationBeanRemote#recommendCard(Integer accountId, Long cardId, Integer[] accountIds, Long[] clusterIds)
	 */
	@Override
	public void recommendCard(Integer accountId, Long cardId, Integer[] accountIds, Long[] clusterIds) {
		try {
			recommendService.recommendCard(accountId, cardId, accountIds, clusterIds);
		} catch (Throwable t) {
			throw new ApplicationException(t.getMessage());
		}		
	}

	@Override
	public int getNewsCount(Integer accountId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Card[] getNews(Integer accountId, String date, boolean forward,
			int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void viewedNews(Integer accountId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getRecommendationsCount(Integer accountId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Recommendation[] getRecommendations(Integer accountId, String date,
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
