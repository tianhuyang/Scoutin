package com.scoutin.logic;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.scoutin.entities.Card;
import com.scoutin.entities.Cardbody;
import com.scoutin.entities.Cardstat;
import com.scoutin.entities.Comment;
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
     * card must have a correct album id and a valid url
     * @see com.scoutin.logic.CardBeanRemote#createCard(com.scoutin.entities.Card)
     */
    
	@Override
	public Card createCard(Map<String,Object> properties) {
		// TODO Auto-generated method stub
		Card card = new Card();
		
		try {			
			BeanUtils.populate(card, properties);
			Cardbody cardBody = new Cardbody();
			BeanUtils.populate(cardBody, properties);	
			
			Cardstat cardStat = new Cardstat();
			DaoUtils.cardStatDao.persist(cardStat);
			cardBody.setCardstat(cardStat);
			DaoUtils.cardBodyDao.persist(cardBody);
			card.setCardbody(cardBody);
		    DaoUtils.cardDAO.persist(card);
			
		} catch (IllegalAccessException e) {
			//e.printStackTrace();
			card = null;
		} catch (InvocationTargetException e) {
			//e.printStackTrace();
			card = null;
		}
		DaoUtils.cardDAO.attachDirty(card);
		return card;
	}

	/*
	 * should have correct cardId and albumId
	 * @see com.scoutin.logic.CardBeanRemote#repostCard(int, long)
	 */
	@Override
	public Card repostCard(int cardId, long albumId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment commentCard(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

}
