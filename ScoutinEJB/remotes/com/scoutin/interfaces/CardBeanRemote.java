package com.scoutin.interfaces;

import java.util.Map;

import javax.ejb.Remote;

import com.scoutin.entities.Card;
import com.scoutin.entities.Cardbody;
import com.scoutin.entities.Comment;

@Remote
public interface CardBeanRemote {

	/*
	 * @param accountId:(Integer) must be existent
	 * @albumIds:(Long[]) must be existent and belong to the accountId
	 * @card:(Card) must be not-null
	 * @cardbody:(Cardbody) must be not-null
	 * @return Card, Card.cardbody if successful otherwise throws Throwable
	 */
	public Card createCard(Integer accountId, Long[] albumIds, Card card, Cardbody cardbody);
	/*
	 * @param accountId:(Integer) must be existent
	 * @param cardbodyId:(Long) must be existent
	 * @param albumIds:(Long[]) must be existent and belongs to the accountId
	 * @return Card, Card.Cardbody if successful otherwise throws Throwable
	 */
	public Card repostCard(Integer accountId, Long[] albumIds, Long cardbodyId);
	/*
	 * @param accountId:(Integer) must be existent
	 * @param cardProperties:(Map<String,Object>) either null or must have cardId:(Long) and version:(Long)
	 * @param cardbodyProperties:(Map<String,Object>) either null or must have cardbodyId:(Long) and version:(Long)
	 * cardProperties and cardbodyProperties can't be both null
	 * @return ? if successful otherwise throws Throwable
	 */
	public Map<String,Object> editCard(Integer accountId, Map<String,Object> cardProperties, Map<String,Object> cardbodyProperties);
	/*
	 * @param accountId:(Integer) must be existent
	 * @param cardId:(Long) must be existent
	 * @param comment:(Comment) must be not null and has content
	 * @return Comment if successful
	 */
	public Comment commentCard(Integer accountId, Long cardId, Comment comment);
	/*
	 * @param accountId:(Integer) must be existent
	 * @param cardId:(Long) must be existent
	 * @return whether the card is liked or disliked
	 */
	public boolean likeCard(Integer accountId, Long cardId);
}
