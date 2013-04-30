package com.scoutin.daos;


import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.scoutin.entities.Card;
import com.scoutin.facades.CardFacade;
import com.scoutin.facades.LogUtil;
@Stateless
public class CardDao extends CardFacade {

	public Card repostCard(int cardId, long albumId) {
		// TODO Auto-generated method stub
		return null;
	}
	
    private static final String cardBelongToAccountHql = "select count(album) from Album album where album.account.accountId = :accountId and album.albumId = any (select ac.id.albumId from Albumcard ac where ac.id.cardId = :cardId)";
	
	public boolean cardBelongToAccount(long cardId, int accountId) {
		LogUtil.log("cardBelongToAccounting", Level.INFO, null);
		try {
			Query query = entityManager.createQuery(cardBelongToAccountHql);
			query.setParameter("accountId", accountId);
			query.setParameter("cardId", cardId);
			long count = (Long)query.getSingleResult();
			LogUtil.log("cardBelongToAccount successful", Level.INFO, null);
			return count >= 1;
		} catch (RuntimeException re) {
			LogUtil.log("cardBelongToAccount failed", Level.SEVERE, re);
			throw re;
		}	
	}
}
