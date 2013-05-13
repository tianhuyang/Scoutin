package com.scoutin.logic;

import java.util.List;

import com.scoutin.entities.Card;
import com.scoutin.exception.ScoutinException;
import com.scoutin.utilities.EJBUtils;

public class RecommendService {

	/*
	 * @param accountId:(Integer) must be existent and authenticated
	 * 
	 * @param cardId:(Long) must belong to accountId
	 * 
	 * @param accountIds:(Integer[]) must be existent or zero-length
	 * 
	 * @param clusterIds:(Long[]) must be existent or zero-length accountIds and
	 * clusterIds can't be both zero-length
	 * 
	 * @throws ScoutinException if failed
	 */
	public static void recommendCard(Integer accountId, Long cardId,
			Integer[] accountIds, Long[] clusterIds) throws ScoutinException {
		if (cardId == null || accountId == null || accountIds == null
				|| clusterIds == null
				|| (accountIds.length == 0 && clusterIds.length == 0)) {
			throw new IllegalArgumentException("Illegal arguments in recommendCard");
		}

		try {
			EJBUtils.recommendBeanRemote.recommendCard(accountId, cardId, accountIds, clusterIds);
		} catch (Throwable re) {
			throw new ScoutinException(
					ScoutinException.Recommend_RecommendCard_Failure_Status,
					ScoutinException.Recommend_RecommendCard_Failure_Message);
		}
	}
	
	/*
	 * @param accountId:(Integer) must be existent and authenticated
	 * @throws ScoutinException if failed
	 */
	public static long getNewsCount(Integer accountId) throws ScoutinException{
		if (accountId == null) {
			throw new IllegalArgumentException("Illegal arguments in getNewsCount");
		}

		try {
			 return EJBUtils.recommendBeanRemote.getNewsCount(accountId);
		} catch (Throwable re) {
			throw new ScoutinException(
					ScoutinException.Recommend_GetNewsCount_Failure_Status,
					ScoutinException.Recommend_GetNewsCount_Failure_Message);
		}
	}
	
	/*
	 * @param accountId:(Integer) must be existent and authenticated
	 * @param date:(long)
	 * @param forward:(boolean)
	 * @param limit:(limit) must greater than 0 and less than MaxNewsLimit
	 * @return unvisited cards, cards.cardBody
	 * @throws ScoutinException if failed
	 * may have same-date problems(too less limit)
	 */
	public static List<Card> getNews(Integer accountId, long date, boolean forward, int limit) throws ScoutinException{
		if (accountId == null || limit < 1) {
			throw new IllegalArgumentException("Illegal arguments in getNewsCount");
		}
		try {
			 return EJBUtils.recommendBeanRemote.getNews(accountId, date, forward, limit);
		} catch (Throwable re) {
			throw new ScoutinException(
					ScoutinException.Recommend_GetNews_Failure_Status,
					ScoutinException.Recommend_GetNews_Failure_Message);
		}
	}
}
