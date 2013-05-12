package com.scoutin.application.interfaces;

import java.util.Map;

import javax.ejb.Remote;

import com.scoutin.entities.Card;
import com.scoutin.entities.CardBody;
import com.scoutin.entities.Comment;

@Remote
public interface CardBeanRemote {

	/*
	 * @param accountId:(Integer) must be existent
	 * @param albumIds:(Long[]) must be existent and belong to the accountId
	 * @card:(Card) must be not-null, null card.cardId, null card.verion
	 * @cardbody:(Cardbody) must be not-null, null cardbody.cardbodyId, null cardbody.version
	 * @return Card, Card.cardbody if successful
	 * @throws ApplicationException if failed
	 */
	public Card createCard(Integer accountId, Long[] albumIds, Card card, CardBody cardbody);
	/*
	 * @param accountId:(Integer) must be existent
	 * @param cardBodyId:(Long) must be existent
	 * @param albumIds:(Long[]) must be existent and belongs to the accountId
	 * @return Card, Card.Cardbody if successful, null card.cardId, null card.version
	 * @throws ApplicationException if failed
	 */
	public Card repostCard(Integer accountId, Long[] albumIds, Card card, Long cardbodyId);
	/*
	 * @param accountId:(Integer) must be existent
	 * @param cardProperties:(Map<String,Object>) either null or must have cardId:(Long)
	 * @param cardbodyProperties:(Map<String,Object>) either null or must have cardBodyId:(Long)
	 * cardProperties and cardbodyProperties can't be both null
	 * @return card:(Map<String,Object>), cardbody:(Map<String,Object>) if successful
	 * @throws ApplicationException if failed
	 */
	public Map<String,Object> editCard(Integer accountId, Map<String,Object> cardProperties, Map<String,Object> cardbodyProperties);
	/*
	 * @param accountId:(Integer) must be existent
	 * @param cardId:(Long) must be existent
	 * @param comment:(Comment) must be not null and has content, null comment.commentId, null comment.version
	 * @return Comment if successful
	 * @throws ApplicationException if failed
	 */
	public Comment commentCard(Integer accountId, Long cardId, Comment comment);
	/*
	 * @param accountId:(Integer) must be existent
	 * @param cardId:(Long) must be existent
	 * @param endorsed:(boolean)
	 * @throws ApplicationException if failed
	 */
	public void endorseCard(Integer accountId, Long cardId, boolean endorsed);
	
}
