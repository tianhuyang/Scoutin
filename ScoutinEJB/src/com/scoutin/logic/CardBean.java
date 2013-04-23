package com.scoutin.logic;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.entities.Albumcard;
import com.scoutin.entities.AlbumcardId;
import com.scoutin.entities.Card;
import com.scoutin.entities.Cardbody;
import com.scoutin.entities.Cardrepost;
import com.scoutin.entities.CardrepostId;
import com.scoutin.entities.Comment;
import com.scoutin.interfaces.CardBeanRemote;
import com.scoutin.utilities.CommonUtils;
import com.scoutin.utilities.DaoUtils;

/**
 * Session Bean implementation class CardBean
 */
@Stateless
@LocalBean
public class CardBean implements CardBeanRemote {
	
	@EJB DaoUtils daoUtils;
	/**
	 * Default constructor.
	 */
	public CardBean() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * card must have a correct albumIds:long[], a valid url:String,
	 *@param accountId:int
	 * 
	 * @see com.scoutin.logic.CardBeanRemote#createCard(com.scoutin.entities.Card)
	 */

	@Override
	public Card createCard(Map<String, Object> properties) {
		// whether all the albumIds exists
		long[] albumIds = (long[]) properties.get("albumIds");
		int accountId = (Integer) properties.get("accountId");
		Long[] lAlbumIds = new Long[albumIds.length];
		CommonUtils.longToLong(lAlbumIds, albumIds);
		if (daoUtils.getAlbumDao().verifyAccountAlbum(accountId, lAlbumIds) == false) {
			throw new IllegalArgumentException(
					"accountId doesn't match or not all albumIds exist");
		}
		Card card = new Card();
		try {
			// populate objects
			BeanUtils.populate(card, properties);
			Cardbody cardBody = new Cardbody();
			BeanUtils.populate(cardBody, properties);

			// insert values
			Account account = daoUtils.getAccountDao().findById(accountId);
			cardBody.setAccount(account);
			daoUtils.getCardBodyDao().save(cardBody);
			card.setCardbody(cardBody);
			daoUtils.getCardDao().save(card);
			for (long albumId : albumIds) {
				AlbumcardId albumCardId = new AlbumcardId();
				albumCardId.setAlbumId(albumId);
				albumCardId.setCardId(card.getCardId());
				Albumcard albumcard = new Albumcard();
				albumcard.setId(albumCardId);
				daoUtils.getAlbumcardDao().save(albumcard);
			}
			//daoUtils.cardDao.evict(card);
		} catch (IllegalAccessException e) {
			card = null;
			// e.printStackTrace();
		} catch (InvocationTargetException e) {
			card = null;
			// e.printStackTrace();
		}

		return card;
	}

	/*
	 * should have non-null properties, correct (reposted) cardbodyId:long,
	 * (reposter's) albumIds:long[] all albumIds should belong to the same
	 * accountId
	 * 
	 * @see com.scoutin.logic.CardBeanRemote#repostCard(int, long)
	 * return Card, Card.Cardbody
	 */
	@Override
	public Card repostCard(Map<String, Object> properties) {
		// whether all the albumIds exists
		long[] albumIds = (long[]) properties.get("albumIds");
		int accountId = (Integer) properties.get("accountId");
		long repostedCardbodyId = (Long)properties.get("cardbodyId");
		Long[] lAlbumIds = new Long[albumIds.length];
		CommonUtils.longToLong(lAlbumIds, albumIds);
		if (daoUtils.getAlbumDao().verifyAccountAlbum(accountId, lAlbumIds) == false) {
			throw new IllegalArgumentException(
					"accountId doesn't match or not all albumIds exist");
		}
		Card card = new Card();
		try {
			// populate objects
			BeanUtils.populate(card, properties);

			// create card
			Cardbody cardBody = daoUtils.getCardBodyDao().findById(repostedCardbodyId);
			card.setCardbody(cardBody);
			card.setCardId(null);
			daoUtils.getCardDao().save(card);
			// create cardReposts
			CardrepostId cardRepostId = new CardrepostId();
			cardRepostId.setAccountId(accountId);
			cardRepostId.setCardId(card.getCardId());			
			Cardrepost cardRepost = daoUtils.getCardRepostDao()
					.findById(cardRepostId);
			if (cardRepost == null) {
				cardRepost = new Cardrepost();
				cardRepost.setId(cardRepostId);
				cardRepost.setCount(1);
			} else {
				daoUtils.getCardBodyDao()
						.increaseRepostsCount(repostedCardbodyId, 1);
			}
			daoUtils.getCardBodyDao().save(cardBody);
			daoUtils.getCardRepostDao().save(cardRepost);

		} catch (IllegalAccessException e) {
			card = null;
			// e.printStackTrace();
		} catch (InvocationTargetException e) {
			card = null;
			// e.printStackTrace();
		}

		return card;
	}

	@Override
	public Comment commentCard(Map<String, Object> properties) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * should have non-null properties, correct cardId:long
	 * 
	 * @see com.scoutin.logic.CardBeanRemote#repostCard(int, long)
	 */
	@Override
	public Map<String, Object> editCard(Map<String, Object> properties) {
		// TODO Auto-generated method stub
		return null;
	}

}