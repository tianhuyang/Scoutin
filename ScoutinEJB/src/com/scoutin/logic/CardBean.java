package com.scoutin.logic;

import java.util.Map;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;

import com.scoutin.application.exception.ApplicationException;
import com.scoutin.application.interfaces.CardBeanRemote;
import com.scoutin.entities.Card;
import com.scoutin.entities.CardBody;
import com.scoutin.entities.Comment;

/**
 * Session Bean implementation class CardBean
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.NEVER)
public class CardBean implements CardBeanRemote {

	@EJB
	private CardService cardService;

	/**
	 * Default constructor.
	 */
	public CardBean() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * @see com.scoutin.logic.CardBeanRemote#createCard(Integer accountId,
	 * Long[] albumIds, Card card)
	 */
	@Override
	public Card createCard(Integer accountId, Long[] albumIds, Card card,
			CardBody cardBody) {
		try {
			card = cardService.createCard(accountId, albumIds, card, cardBody);
			if (card != null) {
				card.setAlbums(null);
				card.getCardBody().setAccount(null);
			}
			return card;
		} catch (Throwable t) {
			throw new ApplicationException(t.getMessage());
		}
	}

	/*
	 * @see com.scoutin.logic.CardBeanRemote#repostCard(Integer accountId,
	 * Long[] albumIds, Long cardBodyId)
	 */
	@Override
	public Card repostCard(Integer accountId, Long[] albumIds, Card card,
			Long cardBodyId) {
		try {
			card = cardService
					.repostCard(accountId, albumIds, card, cardBodyId);
			if (card != null) {
				card.setAlbums(null);
			}
			return card;
		} catch (Throwable t) {
			throw new ApplicationException(t.getMessage());
		}
	}

	/*
	 * @param properties should be non-null , have correct cardId:Long,
	 * accountId:Integer, content:String
	 * 
	 * @see com.scoutin.logic.CardBeanRemote#commentCard(Map<String, Object>)
	 * 
	 * @return comment if successful
	 */

	@Override
	public Comment commentCard(Integer accountId, Long cardId, Comment comment) {
		try {
			comment.setCommentId(null);
			cardService.commentCard(accountId, cardId, comment);
			if (comment != null) {
				comment.setCard(null);
				comment.setAccount(null);
			}
			return comment;
		} catch (Throwable t) {
			throw new ApplicationException(t.getMessage());
		}
	}

	/*
	 * @see com.scoutin.logic.CardBeanRemote#editCard(Integer accountId,
	 * Map<String,Object> cardProperties, Map<String,Object> cardBodyProperties)
	 */
	@Override
	public Map<String, Object> editCard(Integer accountId,
			Map<String, Object> cardProperties,
			Map<String, Object> cardBodyProperties) {
		Map<String, Object> properties = null;
		try {
			properties = cardService.editCard(accountId, cardProperties,
					cardBodyProperties);
		} catch (Throwable t) {
			throw new ApplicationException(t.getMessage());
		}
		Card card = (Card) properties.get("card");
		if (card != null) {
			cardProperties = new TreeMap<String, Object>();
			cardProperties.put("updatedTime", card.getUpdatedTime());
			properties.put("card", cardProperties);
		}

		CardBody cardBody = (CardBody) properties.get("cardBody");
		if (cardBody != null) {
			cardBodyProperties = new TreeMap<String, Object>();
			cardBodyProperties.put("updatedTime", cardBody.getUpdatedTime());
			properties.put("cardBody", cardBodyProperties);
		}
		return properties;
	}

	/*
	 * @see com.scoutin.logic.CardBeanRemote#endorseCard(Integer accountId, Long
	 * cardId, boolean endorsed)
	 */
	@Override
	public void endorseCard(Integer accountId, Long cardId, boolean endorsed) {
		try {
			cardService.endorseCard(accountId, cardId, endorsed);
		} catch (Throwable t) {
			if (t.getCause() instanceof EntityExistsException == false)
				throw new ApplicationException(t.getMessage());
		}
	}

}
