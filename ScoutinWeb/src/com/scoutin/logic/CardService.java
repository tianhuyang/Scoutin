package com.scoutin.logic;

import java.util.Map;

import com.scoutin.entities.Card;
import com.scoutin.exception.ScoutinException;
import com.scoutin.utilities.EJBUtils;

public class CardService {

	public static String cardPath = "ScoutinApplication/Scoutin/CardBean!"
			+ CardBeanRemote.class.getName();
	
	public CardService() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * must have non-null properties, correct albumIds:long[], url:String
	 */

	public static Card createCard(Map<String, Object> properties)
			throws ScoutinException {		
		if (properties == null){
			throw new IllegalArgumentException();
		}
		String url = (String) properties.get("url");
		long[] albumIds = (long[]) properties.get("albumIds");

		if (url == null || url.length() == 0 || albumIds == null || albumIds.length == 0) {
			throw new IllegalArgumentException();
		}

		Card card = null;
		
		try {
			CardBeanRemote cbr = (CardBeanRemote) EJBUtils
					.obtainBean(cardPath);
			card = cbr.createCard(properties);
		} catch (RuntimeException re) {
			//re.printStackTrace();
		}

		if (card == null) {
			throw new ScoutinException(
					ScoutinException.Card_CreateCard_Failure_Status,
					ScoutinException.Card_CreateCard_Failure_Message);
		}
		return card;
	}	
	
	/*
	 * must have non-null properties, correct albumIds, cardId:long
	 */

	public static Card repostCard(Map<String, Object> properties)
			throws ScoutinException {		
		if (properties == null){
			throw new IllegalArgumentException();
		}
		Long cardId = (Long) properties.get("cardId");
		long[] albumIds = (long[]) properties.get("albumIds");

		if (cardId == null || albumIds == null || albumIds.length == 0) {
			throw new IllegalArgumentException();
		}

		Card card = null;
		
		try {
			CardBeanRemote cbr = (CardBeanRemote) EJBUtils
					.obtainBean(cardPath);
			card = cbr.repostCard(properties);
		} catch (RuntimeException re) {
			//re.printStackTrace();
		}

		if (card == null) {
			throw new ScoutinException(
					ScoutinException.Card_CreateCard_Failure_Status,
					ScoutinException.Card_CreateCard_Failure_Message);
		}
		return card;
	}

}
