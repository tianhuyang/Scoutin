package com.scoutin.logic;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

import org.apache.commons.beanutils.BeanUtils;

import com.scoutin.daos.AccountDao;
import com.scoutin.daos.AlbumDao;
import com.scoutin.daos.CardBodyDao;
import com.scoutin.daos.CardDao;
import com.scoutin.daos.CardEndorseDao;
import com.scoutin.daos.CardRepostDao;
import com.scoutin.daos.CommentDao;
import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.entities.Card;
import com.scoutin.entities.CardBody;
import com.scoutin.entities.CardEndorse;
import com.scoutin.entities.CardEndorseId;
import com.scoutin.entities.CardRepost;
import com.scoutin.entities.CardRepostId;
import com.scoutin.entities.Comment;

@Stateless
public class CardService {

	@EJB
	private AlbumDao albumDao;
	@EJB
	private AccountDao accountDao;
	@EJB
	private CardDao cardDao;
	@EJB
	private CardRepostDao cardRepostDao;
	@EJB
	private CardBodyDao cardBodyDao;
	@EJB
	private CardEndorseDao cardEndorseDao;
	@EJB
	private CommentDao commentDao;

	/**
	 * Default constructor.
	 */
	public CardService() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * @see com.scoutin.logic.CardBeanRemote#createCard(Integer accountId,
	 * Long[] albumIds, Card card, CardBody cardBody)
	 */
	public Card createCard(Integer accountId, Long[] albumIds, Card card,
			CardBody cardBody) {
		// whether all the albumIds exists
		if (albumDao.verifyAccountAlbum(accountId, albumIds) == false) {
			throw new IllegalArgumentException(
					"accountId don't match or not all albumIds exist");
		}
		// insert values
		Account account = accountDao.getReference(accountId);
		cardBody.setAccount(account);
		cardBodyDao.save(cardBody);
		card.setCardBody(cardBody);

		for (long albumId : albumIds) {
			Album album = albumDao.getReference(albumId);
			card.getAlbums().add(album);
		}
		cardDao.save(card);

		return card;
	}

	/*
	 * @see com.scoutin.logic.CardBeanRemote#repostCard(Long cardBodyId, Integer
	 * accountId, Long[] albumIds)
	 */
	public Card repostCard(Integer accountId, Long[] albumIds, Card card,
			Long cardBodyId) {
		// whether all the albumIds exists
		if (albumDao.verifyAccountAlbum(accountId, albumIds) == false) {
			throw new IllegalArgumentException(
					"accountId don't match or not all albumIds exist");
		}
		// create card
		CardBody cardBody = cardBodyDao.getReference(cardBodyId);
		card.setCardBody(cardBody);
		for (long albumId : albumIds) {
			Album album = albumDao.getReference(albumId);
			card.getAlbums().add(album);
		}
		cardDao.save(card);
		// create cardReposts
		CardRepostId cardRepostId = new CardRepostId(cardBodyId, accountId);
		CardRepost cardRepost = cardRepostDao.findById(cardRepostId);
		if (cardRepost == null) {
			cardRepost = new CardRepost();
			cardRepost.setId(cardRepostId);
			cardRepostDao.save(cardRepost);
		} else {
			cardRepostDao.increaseCount(cardRepostId, 1);
		}
		cardBodyDao.increaseRepostsCount(cardBodyId, 1);
		return card;
	}

	/*
	 * @see com.scoutin.logic.CardBeanRemote#commentCard(Integer accountId, Long
	 * cardId, Comment comment)
	 */
	public Comment commentCard(Integer accountId, Long cardId, Comment comment) {
		// TODO Auto-generated method stub
		Account account = accountDao.getReference(accountId);
		Card card = cardDao.getReference(cardId);
		comment.setAccount(account);
		comment.setCard(card);
		commentDao.save(comment);
		cardDao.increaseCommentsCount(cardId, 1);
		cardBodyDao.increaseCommentsCountByCardId(cardId, 1);
		return comment;
	}

	/*
	 * @see com.scoutin.logic.CardBeanRemote#editCard(Integer accountId,
	 * Map<String,Object> cardProperties, Map<String,Object> cardBodyProperties)
	 */
	public Map<String, Object> editCard(Integer accountId,
			Map<String, Object> cardProperties,
			Map<String, Object> cardBodyProperties) {
		Long cardId = null;
		Long cardBodyId = null;
		if (cardProperties != null) {
			cardId = (Long) cardProperties.get("cardId");
			if (cardDao.cardBelongToAccount(cardId, accountId) == false) {
				throw new IllegalArgumentException(
						"accountId and cardId don't match or not all exist");
			}
		}
		if (cardBodyProperties != null) {
			cardBodyId = (Long) cardBodyProperties.get("cardBodyId");
			if (cardBodyDao.cardBodyBelongToAccount(cardBodyId, accountId) == false) {
				throw new IllegalArgumentException(
						"accountId and cardBodyId don't match or not all exist");
			}
		}
		Map<String, Object> properties = new TreeMap<String, Object>();
		try {
			if (cardId != null) {
				Card card = cardDao.findById(cardId);
				properties.put("card", card);
				BeanUtils.populate(card, cardProperties);
			}
			if (cardBodyId != null) {
				CardBody cardBody = cardBodyDao.findById(cardBodyId);
				properties.put("cardBody", cardBody);
				BeanUtils.populate(cardBody, cardBodyProperties);
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Unexpeted exception");
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Unexpeted exception");
		}
		return properties;
	}

	/*
	 * @see com.scoutin.logic.CardBeanRemote#endorseCard(Integer accountId, Long
	 * cardId, boolean endorsed)
	 */
	public void endorseCard(Integer accountId, Long cardId, boolean endorsed) {
		CardEndorseId cardendorseId = new CardEndorseId(accountId, cardId);
		if (endorsed) {
			CardEndorse cardendorse = new CardEndorse();
			cardendorse.setId(cardendorseId);
			cardEndorseDao.save(cardendorse);
			cardEndorseDao.flush();
			cardDao.increaseEndorsesCount(cardId, 1);
			cardBodyDao.increaseEndorsesCountByCardId(cardId, 1);
		} else {
			if (cardEndorseDao.removeById(cardendorseId) == 1) {
				cardDao.increaseEndorsesCount(cardId, -1);
				cardBodyDao.increaseEndorsesCountByCardId(cardId, -1);
			}
		}

	}

}
