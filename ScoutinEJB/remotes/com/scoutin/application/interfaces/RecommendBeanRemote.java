package com.scoutin.application.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.scoutin.entities.Card;
import com.scoutin.entities.Recommendation;

@Remote
public interface RecommendBeanRemote {
	
	public static final int MaxNewsLimit = 100;
	public static final int MaxRecommendationsLimit = 100;
	/*
	 * @param accountId:(Integer) must be existent
	 * @param cardId:(Long) must be existent and belongs to accountId
	 * @param accountIds:(Integer[]) must be existent or zero-length
	 * @param clusterIds:(Long[]) must be existent or zero-length
	 * accountIds and clusterIds can't be both zero-long
	 * @throws ApplicationException if failed
	 */
	public void recommendCard(Integer accountId, Long cardId, Integer[] accountIds, Long[] clusterIds);
	
	/*
	 * @param accountId:(Integer) must be existent
	 * @return unvisited news count
	 * @throws ApplicationException if failed
	 */
	public long getNewsCount(Integer accountId);
	
	/*
	 * @param accountId:(Integer) must be existent
	 * @param date:(long)
	 * @param forward:(boolean)
	 * @param limit:(limit) must greater than 0 and less than MaxNewsLimit
	 * @return unvisited cards, cards.cardBody
	 * @throws ApplicationException if failed
	 * may have same-date problems(too less limit)
	 */
	public List<Card> getNews(Integer accountId, long date, boolean forward, int limit);
	
	/*
	 * @param accountId:(Integer) must be existent
	 * @throws ApplicationException if failed
	 */
	public void viewedNews(Integer accountId);
	
	/*
	 * @param accountId:(Integer) must be existent
	 * @return unvisited recommendations count
	 * @throws ApplicationException if failed
	 */
	public long getRecommendationsCount(Integer accountId);
	
	/*
	 * @param accountId:(Integer) must be existent
	 * @param date:(long)
	 * @param forward:(boolean)
	 * @param viewed:(Boolean) may be null
	 * @param limit:(limit) must greater than 0 and less than MaxRecommendationsLimit
	 * @return unvisited recommendations, recommendations.card, recommendations.card.cardBody
	 * @throws ApplicationException if failed
	 * may have same-date problems(too less limit)
	 */
	public List<Recommendation> getRecommendations(Integer accountId, long date, boolean forward, Boolean viewed, int limit);
	

	/*
	 * @param accountId:(Integer) must be existent
	 * @param recommendationIds:(Long[]) must be existent 
	 * @throws ApplicationException if failed
	 */
	public void viewedRecommenations(Integer accountId, Long[] recommendationIds, boolean viewed);
}
