package com.scoutin.logic;

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
					ScoutinException.Card_RecommendCard_Failure_Status,
					ScoutinException.Card_RecommendCard_Failure_Message);
		}
	}
}
