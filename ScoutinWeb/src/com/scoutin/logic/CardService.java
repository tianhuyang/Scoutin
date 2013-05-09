package com.scoutin.logic;

import java.util.Map;
import java.util.TreeMap;

import com.scoutin.entities.Card;
import com.scoutin.entities.CardBody;
import com.scoutin.entities.Comment;
import com.scoutin.exception.ScoutinException;
import com.scoutin.utilities.EJBUtils;

public class CardService {

	public CardService() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * @param accountId:(Integer) must be existent and authenticated
	 * 
	 * @albumIds:(Long[]) must be existent and belong to the accountId
	 * 
	 * @card:(Card) must be not-null
	 * 
	 * @cardBody:(CardBody) must be not-null
	 * 
	 * @return Card, Card.cardBody if successful otherwise throws
	 * ScoutinException
	 */

	public static Card createCard(Integer accountId, Long[] albumIds,
			Card card, CardBody cardBody) throws ScoutinException {
		if (accountId == null || albumIds == null || albumIds.length == 0
				|| card == null || cardBody == null) {
			throw new IllegalArgumentException(
					"Illegal arguments in createCard");
		}
		card.setCardId(null);

		cardBody.setCardBodyId(null);
		try {
			card = EJBUtils.cardBeanRemote.createCard(accountId, albumIds,
					card, cardBody);
		} catch (Throwable re) {
			throw new ScoutinException(
					ScoutinException.Card_CreateCard_Failure_Status,
					ScoutinException.Card_CreateCard_Failure_Message);
		}

		return card;
	}

	/*
	 * @param accountId:(Integer) must be existent and authenticated
	 * 
	 * @param albumIds:(Long[]) must be existent and belongs to the accountId
	 * 
	 * @param cardBodyId:(Long) must be existent
	 * 
	 * @return Card, Card.CardBody if successful otherwise throws
	 * ScoutinException
	 */

	public static Card repostCard(Integer accountId, Long[] albumIds,
			Card card, Long cardBodyId) throws ScoutinException {
		if (accountId == null || albumIds == null || albumIds.length == 0
				|| cardBodyId == null) {
			throw new IllegalArgumentException(
					"Illegal arguments in repostCard");
		}
		card.setCardId(null);

		try {
			card = EJBUtils.cardBeanRemote.repostCard(accountId, albumIds,
					card, cardBodyId);
		} catch (Throwable re) {
			throw new ScoutinException(
					ScoutinException.Card_RepostCard_Failure_Status,
					ScoutinException.Card_RepostCard_Failure_Message);
		}
		return card;
	}

	/*
	 * @param accountId:(Integer) must be existent and authenticated
	 * 
	 * @param cardId:(Long) must be existent
	 * 
	 * @param comment:(Comment) must be not null and has content
	 * 
	 * @return Comment if successful otherwise throws ScoutinException
	 */

	public static Comment commentCard(Integer accountId, Long cardId,
			Comment comment) throws ScoutinException {
		if (accountId == null || cardId == null || comment == null) {
			throw new IllegalArgumentException(
					"Illegal arguments in commentCard");
		}
		comment.setCommentId(null);

		try {
			comment = EJBUtils.cardBeanRemote.commentCard(accountId, cardId,
					comment);
		} catch (Throwable re) {
			comment = null;
		}

		if (comment == null) {
			throw new ScoutinException(
					ScoutinException.Card_CommentCard_Failure_Status,
					ScoutinException.Card_CommentCard_Failure_Message);
		}
		return comment;
	}

	/*
	 * @param accountId:(Integer) must be existent and authenticated
	 * 
	 * @param cardProperties:(Map<String,Object>) either null or must have
	 * cardId:(Long)
	 * 
	 * @param cardBodyProperties:(Map<String,Object>) either null or must have
	 * cardBodyId:(Long) cardProperties and cardBodyProperties can't be both
	 * null
	 * 
	 * @param properties:(Map<String,Object>[], size=1) may have card:(Card) and
	 * cardBody:(CardBody)
	 * 
	 * @throws ScoutinException if failed
	 */
	public static Map<String, Object> editCard(Integer accountId,
			Map<String, Object> cardProperties,
			Map<String, Object> cardBodyProperties) throws ScoutinException {
		if (accountId == null
				|| (cardProperties == null && cardBodyProperties == null)) {
			throw new IllegalArgumentException("Illegal arguments in editCard");
		}
		boolean legal = true;
		if (cardProperties != null) {
			Long cardId = (Long) cardProperties.get("cardId");
			Long version = (Long) cardProperties.get("version");
			if (cardId == null || version == null) {
				legal = false;
			}
		}
		if (cardBodyProperties != null) {
			Long cardBodyId = (Long) cardBodyProperties.get("cardBodyId");
			Long version = (Long) cardBodyProperties.get("version");
			if (cardBodyId == null || version == null) {
				legal = false;
			}
		}
		if (legal == false) {
			throw new IllegalArgumentException("Illegal argument in editCard");
		}
		Map<String, Object> properties = new TreeMap<String, Object>();
		try {
			properties = EJBUtils.cardBeanRemote.editCard(accountId,
					cardProperties, cardBodyProperties);
		} catch (Throwable re) {
			// re.printStackTrace();
			properties = new TreeMap<String, Object>();
			throw new ScoutinException(
					ScoutinException.Card_EditCard_Failure_Status,
					ScoutinException.Card_EditCard_Failure_Message);
		}
		return properties;
	}

	/*
	 * @param accountId:(Integer) must be existent and authenticated
	 * 
	 * @param cardId:(Long) must be existent
	 * 
	 * @return whether the card is endorsed or unendorsed
	 * 
	 * @throws ScoutinException if failed
	 */
	public static boolean endorseCard(Integer accountId, Long cardId)
			throws ScoutinException {
		if (cardId == null || accountId == null) {
			throw new IllegalArgumentException("Illegal arguments in editCard");
		}

		boolean endorsed = false;
		try {
			endorsed = EJBUtils.cardBeanRemote.endorseCard(accountId, cardId);
		} catch (Throwable re) {
			throw new ScoutinException(
					ScoutinException.Card_LikeCard_Failure_Status,
					ScoutinException.Card_LikeCard_Failure_Message);
		}
		return endorsed;
	}

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
			EJBUtils.cardBeanRemote.recommendCard(accountId, cardId, accountIds, clusterIds);
		} catch (Throwable re) {
			throw new ScoutinException(
					ScoutinException.Card_RecommendCard_Failure_Status,
					ScoutinException.Card_RecommendCard_Failure_Message);
		}
	}

}
