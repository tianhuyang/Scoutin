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
	 * @return Card
	 */

	@Override
	public Card createCard(Map<String, Object> properties) {
		// whether all the albumIds exists
		long[] albumIds = (long[]) properties.get("albumIds");
		int accountId = (Integer) properties.get("accountId");
		if (daoUtils.getAlbumDao().verifyAccountAlbum(accountId, albumIds) == false) {
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
			Account account = daoUtils.getAccountDao().getReference(accountId);
			cardBody.setAccount(account);
			daoUtils.getCardBodyDao().save(cardBody);
			card.setCardbody(cardBody);
			
			for (long albumId : albumIds) {
				Album album = daoUtils.getAlbumDao().getReference(albumId);
				card.getAlbums().add(album);
			}
			daoUtils.getCardDao().save(card);
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
		
		if (daoUtils.getAlbumDao().verifyAccountAlbum(accountId, albumIds) == false) {
			throw new IllegalArgumentException(
					"accountId doesn't match or not all albumIds exist");
		}
		Card card = new Card();
		try {
			// populate objects
			BeanUtils.populate(card, properties);

			// create card
			Cardbody cardBody = daoUtils.getCardBodyDao().getReference(repostedCardbodyId);
			card.setCardbody(cardBody);
			daoUtils.getCardDao().save(card);
			// create cardReposts
			CardrepostId cardRepostId = new CardrepostId(repostedCardbodyId,accountId);		
			Cardrepost cardRepost = daoUtils.getCardRepostDao()
					.findById(cardRepostId);
			if (cardRepost == null) {
				cardRepost = new Cardrepost();
				cardRepost.setId(cardRepostId);
				cardRepost.setCount(1);
				//card.getCardreposts().add(cardRepost);
				daoUtils.getCardRepostDao().save(cardRepost);
			} else {
				daoUtils.getCardRepostDao().increaseCount(cardRepostId, 1);
			}
			daoUtils.getCardBodyDao().increaseRepostsCount(repostedCardbodyId, 1);
			//daoUtils.getCardBodyDao().save(cardBody);
			

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
