package com.scoutin.daos;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.scoutin.entities.Card;
import com.scoutin.facades.LogUtil;
import com.scoutin.facades.RecommendationFacade;

@Stateless
public class RecommendationDao extends RecommendationFacade {

	private static final String getNewsCountJPQL = "select count(distinct card) from Follower follower, Album album, AlbumCard ac, Card card, AccountStat accountStat where follower.id.followingId = ?1 and follower.id.followedId = album.account.accountId and ac.id.albumId = album.albumId and not exists(select ba.createdTime from BlockedAlbum ba where ba.follower = follower and ba.album = album) and card.cardId = ac.id.cardId and accountStat.accountId = ?1 and card.createdTime > accountStat.lastNewsVisited order by card.createdTime desc";

	public long getNewsCount(Integer accountId) {
		LogUtil.log("getNewsCounting with accountId = " + accountId,
				Level.INFO, null);
		Long count = 0L;
		try {
			Query query = entityManager.createQuery(getNewsCountJPQL);
			query.setParameter(1, accountId);
			count = (Long) query.getSingleResult();
			LogUtil.log("getNewsCount successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("getNewsCount failed", Level.SEVERE, re);
			throw re;
		}
		return count;
	}

	private static final String getForwardNewsJPQL = "select card from Follower follower, Album album, AlbumCard ac, Card card join fetch card.cardBody where follower.id.followingId = :accountId and follower.id.followedId = album.account.accountId and ac.id.albumId = album.albumId and not exists(select ba.createdTime from BlockedAlbum ba where ba.follower = follower and ba.album = album) and card.cardId = ac.id.cardId and card.createdTime > :date  order by card.createdTime desc";
	private static final String getBackwardNewsJPQL = "select card from Follower follower, Album album, AlbumCard ac, Card card join fetch card.cardBody where follower.id.followingId = :accountId and follower.id.followedId = album.account.accountId and ac.id.albumId = album.albumId and not exists(select ba.createdTime from BlockedAlbum ba where ba.follower = follower and ba.album = album) and card.cardId = ac.id.cardId and card.createdTime < :date  order by card.createdTime desc";

	public List<Card> getNews(Integer accountId, long date, boolean forward, int limit){
		LogUtil.log("getNewsing with accountId = " + accountId,
				Level.INFO, null);
		List<Card> cards = null;
		try {
			String string = null;
			if(forward){
				string = getForwardNewsJPQL;
			}
			else{
				string = getBackwardNewsJPQL;
			}
			Query query = entityManager.createQuery(string);
			query.setParameter("accountId", accountId);
			query.setParameter("date", new Timestamp(date));
			query.setMaxResults(limit);
			cards = query.getResultList();
			LogUtil.log("getNews successful", Level.INFO, null);
			return cards;
		} catch (RuntimeException re) {
			LogUtil.log("getNews failed", Level.SEVERE, re);
			throw re;
		}
	}
}
