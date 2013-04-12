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
	 * must have non-null properties, correct albumId, url
	 */

	public static Card createCard(Map<String, Object> properties)
			throws ScoutinException {		
		if (properties == null){
			throw new IllegalArgumentException();
		}
		String url = (String) properties.get("url");
		Long albumId = (Long) properties.get("albumId");

		if (url == null || url.length() == 0 || albumId == null) {
			throw new IllegalArgumentException();
		}

		Card card = null;
		CardBeanRemote cbr = (CardBeanRemote) EJBUtils
				.obtainBean(cardPath);
		try {
			card = cbr.createCard(properties);
		} catch (RuntimeException re) {
		}

		if (card == null) {
			throw new ScoutinException(
					ScoutinException.Account_Signup_Failure_Status,
					ScoutinException.Account_Signup_Failure_Message);
		}
		return card;
	}	

}
