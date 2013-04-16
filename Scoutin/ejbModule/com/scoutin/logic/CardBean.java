package com.scoutin.logic;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import org.apache.commons.beanutils.BeanUtils;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.scoutin.entities.Album;
import com.scoutin.entities.Albumcard;
import com.scoutin.entities.AlbumcardId;
import com.scoutin.entities.Card;
import com.scoutin.entities.Cardbody;
import com.scoutin.entities.Cardreposts;
import com.scoutin.entities.CardrepostsId;
import com.scoutin.entities.Comment;
import com.scoutin.utilities.CommonUtils;
import com.scoutin.utilities.DaoUtils;

/**
 * Session Bean implementation class CardBean
 */
@Stateless
@LocalBean
public class CardBean implements CardBeanRemote {

	/**
	 * Default constructor.
	 */
	public CardBean() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * card must have a correct albumIds:long[] and a valid url:String
	 * 
	 * @see
	 * com.scoutin.logic.CardBeanRemote#createCard(com.scoutin.entities.Card)
	 */

	@Override
	public Card createCard(Map<String, Object> properties) {
		// whether all the albumIds exists
		long[] albumIds = (long[])properties.get("albumIds");
		Long[] lAlbumIds = new Long[albumIds.length];
		CommonUtils.longToLong(lAlbumIds, albumIds);
		if (DaoUtils.albumDao.hasAll(lAlbumIds) == false) {
			throw new IllegalArgumentException("not all albumIds exist");
		}
		Card card = new Card();	
		try {
			// populate objects
			BeanUtils.populate(card, properties);
			Cardbody cardBody = new Cardbody();
			BeanUtils.populate(cardBody, properties);

			// insert values
			DaoUtils.cardBodyDao.save(cardBody);
			card.setCardbody(cardBody);
			DaoUtils.cardDao.save(card);
			for (long albumId : albumIds) {
				Albumcard albumcard = new Albumcard(new AlbumcardId(albumId,
						card.getCardId()));
				DaoUtils.albumcardDao.persist(albumcard);
			}

			DaoUtils.cardDao.evict(card);
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
	 * should have non-null properties, correct (reposted) cardId:long, (reposter's) albumIds:long[]
	 * all albumIds should belong to the same accountId
	 * @see com.scoutin.logic.CardBeanRemote#repostCard(int, long)
	 */
	@Override
	public Card repostCard(Map<String, Object> properties) {
		long[] albumIds = (long[])properties.get("albumIds");
		Long[] lAlbumIds = new Long[albumIds.length];
		CommonUtils.longToLong(lAlbumIds, albumIds);
		if (DaoUtils.albumDao.hasAll(lAlbumIds) == false) {
			throw new IllegalArgumentException("not all albumIds exist");
		}
		Card card = new Card();
		try {
			//populate objects
			BeanUtils.populate(card, properties); 
			//get accountId
			int accountId = DaoUtils.albumDao.getAccountIdId(albumIds[0]);
			//create card
			Long repostedCardId = card.getCardId();
			Long repostedCardbodyId = DaoUtils.cardDao.getCardbodyIdId(repostedCardId);
			Cardbody cardBody = DaoUtils.cardBodyDao.load(repostedCardbodyId);
			card.setCardbody(cardBody);
			card.setCardId(null);			
			DaoUtils.cardDao.save(card);
			//create cardReposts
			CardrepostsId cardRepostsId = new CardrepostsId(accountId,card.getCardId());
			Cardreposts cardReposts = DaoUtils.cardRepostsDato.findById(cardRepostsId);
			if(cardReposts == null){
				cardReposts = new Cardreposts();
				cardReposts.setId(cardRepostsId);
				cardReposts.setCount(1);
			}
			else{
				DaoUtils.cardBodyDao.increaseRepostsCount(repostedCardbodyId, 1);
			}
			DaoUtils.cardBodyDao.persist(cardBody);
			DaoUtils.cardRepostsDato.persist(cardReposts);

		}  catch (IllegalAccessException e) {
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
