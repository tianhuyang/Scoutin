package com.scoutin.logic;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.scoutin.entities.Account;
import com.scoutin.entities.Card;
import com.scoutin.entities.Comment;
import com.scoutin.homes.AccountHome;
import com.scoutin.utilities.DAOUtils;

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
     * card should have correct album id
     * @see com.scoutin.logic.CardBeanRemote#createCard(com.scoutin.entities.Card)
     */
    
	@Override
	public Card createCard(Card card) {
		// TODO Auto-generated method stub
		DAOUtils.cardHome.attachDirty(card);
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
