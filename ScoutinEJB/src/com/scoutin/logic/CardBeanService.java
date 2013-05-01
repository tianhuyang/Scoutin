package com.scoutin.logic;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.OptimisticLockException;

import org.apache.commons.beanutils.BeanUtils;

import com.scoutin.daos.AccountDao;
import com.scoutin.daos.AlbumDao;
import com.scoutin.daos.CardBodyDao;
import com.scoutin.daos.CardDao;
import com.scoutin.daos.CardLikeDao;
import com.scoutin.daos.CardRepostDao;
import com.scoutin.daos.CommentDao;
import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.entities.Card;
import com.scoutin.entities.Cardbody;
import com.scoutin.entities.Cardlike;
import com.scoutin.entities.CardlikeId;
import com.scoutin.entities.Cardrepost;
import com.scoutin.entities.CardrepostId;
import com.scoutin.entities.Comment;

@Stateless
public class CardBeanService {

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
	private CardLikeDao cardLikeDao;
	@EJB
	private CommentDao commentDao;

	/**
	 * Default constructor.
	 */
	public CardBeanService() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * @see com.scoutin.logic.CardBeanRemote#createCard(Integer accountId,
	 * Long[] albumIds, Card card, Cardbody cardbody)
	 */
	public Card createCard(Integer accountId, Long[] albumIds, Card card,
			Cardbody cardbody) {
		// whether all the albumIds exists
		if (albumDao.verifyAccountAlbum(accountId, albumIds) == false) {
			throw new IllegalArgumentException(
					"accountId doesn't match or not all albumIds exist");
		}
		// insert values
		Account account = accountDao.getReference(accountId);
		cardbody.setAccount(account);
		cardBodyDao.save(cardbody);
		card.setCardbody(cardbody);

		for (long albumId : albumIds) {
			Album album = albumDao.getReference(albumId);
			card.getAlbums().add(album);
		}
		cardDao.save(card);

		return card;
	}

	/*
	 * @see com.scoutin.logic.CardBeanRemote#repostCard(Long cardbodyId, Integer
	 * accountId, Long[] albumIds)
	 */
	public Card repostCard(Integer accountId, Long[] albumIds, Card card, Long cardbodyId) {
		// whether all the albumIds exists
		if (albumDao.verifyAccountAlbum(accountId, albumIds) == false) {
			throw new IllegalArgumentException(
					"accountId doesn't match or not all albumIds exist");
		}
		// create card
		Cardbody cardBody = cardBodyDao.getReference(cardbodyId);
		card.setCardbody(cardBody);
		for (long albumId : albumIds) {
			Album album = albumDao.getReference(albumId);
			card.getAlbums().add(album);
		}
		cardDao.save(card);
		// create cardReposts
		CardrepostId cardRepostId = new CardrepostId(cardbodyId, accountId);
		Cardrepost cardRepost = cardRepostDao.findById(cardRepostId);
		if (cardRepost == null) {
			cardRepost = new Cardrepost();
			cardRepost.setId(cardRepostId);
			cardRepostDao.save(cardRepost);
		} else {
			cardRepostDao.increaseCount(cardRepostId, 1);
		}
		cardBodyDao.increaseRepostsCount(cardbodyId, 1);
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
	 * Map<String,Object> cardProperties, Map<String,Object> cardbodyProperties)
	 */
	public void editCard(Integer accountId,
			Map<String, Object> cardProperties,
			Map<String, Object> cardbodyProperties, Map<String, Object> properties) {
		Long cardId = null;
		Long cardbodyId = null;
		Long version = null;
		if (cardProperties != null) {
			cardId = (Long) cardProperties.get("cardId");
			if (cardDao.cardBelongToAccount(cardId, accountId) == false) {
				throw new IllegalArgumentException(
						"accountId and cardId doesn't match or not all exist");
			}
		}
		if (cardbodyProperties != null) {
			cardbodyId = (Long) cardbodyProperties.get("cardbodyId");
			if (cardBodyDao.cardbodyBelongToAccount(cardbodyId, accountId) == false) {
				throw new IllegalArgumentException(
						"accountId and cardbodyId doesn't match or not all exist");
			}
		}		
		try {
			if (cardId != null) {
				version = (Long) cardProperties.remove("version");
				Card card = cardDao.findById(cardId);
				properties.put("card", card);
				if (version.equals(card.getVersion()) == false) {
					throw new OptimisticLockException(
							"OptimisticLockException while updating card");
				}
				BeanUtils.populate(card, cardProperties);

			}
			if (cardbodyId != null) {
				version = (Long) cardbodyProperties.remove("version");
				Cardbody cardbody = cardBodyDao.findById(cardbodyId);
				properties.put("cardbody", cardbody);
				if (version.equals(cardbody.getVersion()) == false) {
					throw new OptimisticLockException(
							"OptimisticLockException while updating cardbody");
				}
				BeanUtils.populate(cardbody, cardbodyProperties);
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Unexpeted exception");
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Unexpeted exception");
		}
	}

	/*
	 * @see com.scoutin.logic.CardBeanRemote#likeCard(Integer accountId, Long
	 * cardId)
	 */
	public boolean likeCard(Integer accountId, Long cardId) {
		boolean liked;
		CardlikeId cardlikeId = new CardlikeId(accountId, cardId);
		Cardlike cardlike = cardLikeDao.findById(cardlikeId);
		if (cardlike == null) {
			cardlike = new Cardlike();
			cardlike.setId(cardlikeId);
			cardLikeDao.save(cardlike);
			cardDao.increaseLikesCount(cardId, 1);
			cardBodyDao.increaseLikesCountByCardId(cardId, 1);
			liked = true;
		} else {
			cardLikeDao.remove(cardlike);
			cardDao.increaseLikesCount(cardId, -1);
			cardBodyDao.increaseLikesCountByCardId(cardId, -1);
			liked = false;
		}
		return liked;
	}
}
