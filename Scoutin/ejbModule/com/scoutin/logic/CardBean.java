package com.scoutin.logic;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.scoutin.daos.AccountHome;
import com.scoutin.entities.Account;
import com.scoutin.entities.Card;
import com.scoutin.utilities.HibernateUtils;

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

	@Override
	public Card createCard(Card card) {
		// TODO Auto-generated method stub
		card = HibernateUtils.accountHome.attachDirty(card);
		return card;
	}

}
