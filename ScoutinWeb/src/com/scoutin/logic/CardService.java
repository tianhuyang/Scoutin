package com.scoutin.logic;

import java.util.Map;
import java.util.TreeMap;

import com.scoutin.entities.Card;
import com.scoutin.entities.Cardbody;
import com.scoutin.entities.Comment;
import com.scoutin.exception.ScoutinException;
import com.scoutin.utilities.EJBUtils;

public class CardService {
	
	public CardService() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * @param accountId:(Integer) must be existent and authenticated
	 * @albumIds:(Long[]) must be existent and belong to the accountId
	 * @card:(Card) must be not-null
	 * @cardbody:(Cardbody) must be not-null
	 * @return Card, Card.cardbody if successful otherwise throws ScoutinException
	 */

	public static Card createCard(Integer accountId, Long[] albumIds, Card card, Cardbody cardbody)
			throws ScoutinException {		
		if (accountId == null || albumIds == null || albumIds.length == 0 || card == null || cardbody == null || card.getCardId()!= null || cardbody.getCardbodyId()!=null){
			throw new IllegalArgumentException("Illegal arguments in createCard");
		}
		try {
			card = EJBUtils.cardBeanRemote.createCard(accountId, albumIds, card, cardbody);
		} catch (Throwable re) {
			card = null;
		}

		if (card == null) {
			throw new ScoutinException(
					ScoutinException.Card_CreateCard_Failure_Status,
					ScoutinException.Card_CreateCard_Failure_Message);
		}
		return card;
	}	
	
	/*
	 * @param accountId:(Integer) must be existent and authenticated
	 * @param albumIds:(Long[]) must be existent and belongs to the accountId
	 * @param cardbodyId:(Long) must be existent
	 * @return Card, Card.Cardbody if successful otherwise throws ScoutinException
	 */

	public static Card repostCard(Integer accountId, Long[] albumIds, Card card, Long cardbodyId)
			throws ScoutinException {		
		if (accountId == null || albumIds == null || albumIds.length == 0 || cardbodyId == null){
			throw new IllegalArgumentException("Illegal arguments in repostCard");
		}

		try {
			card = EJBUtils.cardBeanRemote.repostCard(accountId, albumIds, card, cardbodyId);
		} catch (Throwable re) {
			throw new ScoutinException(
					ScoutinException.Card_RepostCard_Failure_Status,
					ScoutinException.Card_RepostCard_Failure_Message);
		}
		return card;
	}
	
	/*
	 * @param accountId:(Integer) must be existent and authenticated
	 * @param cardId:(Long) must be existent
	 * @param comment:(Comment) must be not null and has content
	 * @return Comment if successful otherwise throws ScoutinException
	 */

	public static Comment commentCard(Integer accountId, Long cardId, Comment comment)
			throws ScoutinException {		
		if (accountId == null || cardId == null || comment == null){
			throw new IllegalArgumentException("Illegal arguments in commentCard");
		}
		try {
			comment = EJBUtils.cardBeanRemote.commentCard(accountId, cardId, comment);
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
	 * @param cardProperties:(Map<String,Object>) either null or must have cardId:(Long) and version:(Long)
	 * @param cardbodyProperties:(Map<String,Object>) either null or must have cardbodyId:(Long) and version:(Long)
	 * cardProperties and cardbodyProperties can't be both null
	 * @param properties:(Map<String,Object>[], size=1) may have card:(Card) and cardbody:(Cardbody) if successful or "ModifiedByOthers"
	 * @throws ScoutinException if failed
	 */
	public static void editCard(Integer accountId, Map<String,Object> cardProperties, Map<String,Object> cardbodyProperties,
			Map<String,Object> properties[])
			throws ScoutinException {		
		if (accountId == null || (cardProperties == null && cardbodyProperties == null)){
			throw new IllegalArgumentException("Illegal arguments in editCard");
		}
		boolean legal = true;
		if(cardProperties != null){
			Long cardId = (Long) cardProperties.get("cardId");
			Long version = (Long) cardProperties.get("version");
			if (cardId == null || version == null) {
				legal = false;
			}
		}
		if(cardbodyProperties != null){	
			Long cardbodyId = (Long) cardbodyProperties.get("cardbodyId");
			Long version = (Long) cardbodyProperties.get("version");
			if (cardbodyId == null || version == null) {
				legal = false;
			}
		}
		if (legal == false) {
			throw new IllegalArgumentException("Illegal argument in editCard");
		}		
		
		try {
			properties[0] = EJBUtils.cardBeanRemote.editCard(accountId, cardProperties, cardbodyProperties);
		} catch (Throwable re) {
			//re.printStackTrace();
			properties[0] = new TreeMap<String, Object>();
			throw new ScoutinException(
					ScoutinException.Card_EditCard_Failure_Status,
					ScoutinException.Card_EditCard_Failure_Message);
		}
		if(properties[0].containsKey("ModifiedByOthers")){
			throw new ScoutinException(
					ScoutinException.Card_EditCard_Modified_Status,
					ScoutinException.Card_EditCard_Modified_Message);
		}
	}
	
	/*
	 * @param accountId:(Integer) must be existent and authenticated
	 * @param cardId:(Long) must be existent
	 * @return whether the card is liked or disliked otherwise throws ScoutinException
	 */
	public static boolean likeCard(Integer accountId, Long cardId)
			throws ScoutinException {		
		if (cardId == null || accountId == null) {
			throw new IllegalArgumentException("Illegal arguments in editCard");
		}
		
		boolean liked = false;
		try {
			liked = EJBUtils.cardBeanRemote.likeCard(accountId, cardId);
		} catch (Throwable re) {
			throw new ScoutinException(
					ScoutinException.Card_LikeCard_Failure_Status,
					ScoutinException.Card_LikeCard_Failure_Message);
		}
		return liked;
	}

}
