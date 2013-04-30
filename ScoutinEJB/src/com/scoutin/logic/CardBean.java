package com.scoutin.logic;

import java.util.Map;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.OptimisticLockException;

import com.scoutin.application.exception.ApplicationException;
import com.scoutin.application.interfaces.CardBeanRemote;
import com.scoutin.entities.Card;
import com.scoutin.entities.Cardbody;
import com.scoutin.entities.Comment;

/**
 * Session Bean implementation class CardBean
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.NEVER)
public class CardBean implements CardBeanRemote {

	@EJB
	private CardBeanService cardBeanService;

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
			Cardbody cardbody) {
		try {
			card.setCardId(null);
			card = cardBeanService.createCard(accountId, albumIds, card,
					cardbody);
			if (card != null) {
				card.setAlbums(null);
				card.getCardbody().setAccount(null);
			}			
			return card;
		} catch (Throwable t) {
			throw new ApplicationException(t.getMessage());
		}
	}

	/*
	 * @see com.scoutin.logic.CardBeanRemote#repostCard(Integer accountId,
	 * Long[] albumIds, Long cardbodyId)
	 */
	@Override
	public Card repostCard(Integer accountId, Long[] albumIds, Card card, Long cardbodyId) {
		try {
			card = cardBeanService.repostCard(accountId, albumIds,card,
					cardbodyId);
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
			cardBeanService.commentCard(accountId, cardId, comment);
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
	 * Map<String,Object> cardProperties, Map<String,Object> cardbodyProperties)
	 */
	@Override
	public Map<String, Object> editCard(Integer accountId,
			Map<String, Object> cardProperties,
			Map<String, Object> cardbodyProperties) {
		Map<String, Object> properties = new TreeMap<String, Object>();
		try {
			cardBeanService.editCard(accountId, cardProperties,
					cardbodyProperties, properties);
		} catch (javax.ejb.EJBException ejbe) {
			if (ejbe.getCause() instanceof OptimisticLockException)
				properties.put("ModifiedByOthers", true);
			else
				throw new ApplicationException(ejbe.getMessage());
		} catch (Throwable t) {
			throw new ApplicationException(t.getMessage());
		}
		return properties;
	}

	/*
	 * @see com.scoutin.logic.CardBeanRemote#likeCard(Integer accountId, Long
	 * cardId)
	 */
	@Override
	public boolean likeCard(Integer accountId, Long cardId) {
		try {
			return cardBeanService.likeCard(accountId, cardId);
		} catch (Throwable t) {
			throw new ApplicationException(t.getMessage());
		}
	}

}
