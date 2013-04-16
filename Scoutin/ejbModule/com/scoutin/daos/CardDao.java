package com.scoutin.daos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.scoutin.entities.Account;
import com.scoutin.entities.Card;
import com.scoutin.entities.CardHome;
import com.scoutin.utilities.DaoUtils;

public class CardDao extends CardHome {

	private static final Log log = LogFactory.getLog(CardHome.class);
	public Card repostCard(int cardId, long albumId) {
		// TODO Auto-generated method stub
		return null;
	}
	
//    private final String cardBodyIdHql = "select cardbodyId from Card where cardId = :cardId";
//	
//	public long getCardbodyId(long cardId) {
//		log.debug("authenticate with email");
//		long cardbodyId = 0L;
//		try {
//			Query query = DaoUtils.sessionFactory.getCurrentSession().createQuery(cardBodyIdHql);
//			query.setParameter("cardId", cardId); 
//			cardbodyId = (Long)query.uniqueResult();
//			log.debug("authenticate successful");
//		} catch (RuntimeException re) {
//			log.error("authenticate failed", re);
//			throw re;
//		}
//		return cardbodyId;		
//	}
}
