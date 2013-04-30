package com.scoutin.daos;

import java.util.Arrays;
import java.util.logging.Level;


import javax.ejb.Stateless;
import javax.persistence.Query;

import com.scoutin.entities.Account;
import com.scoutin.entities.Follower;
import com.scoutin.facades.AlbumFacade;
import com.scoutin.facades.LogUtil;
import com.scoutin.utilities.CommonUtils;
@Stateless
public class AlbumDao extends AlbumFacade {

	private static final String verifyCreateCardHql = "select count(album) from Album album where album.account.accountId = :accountId and album.albumId in (:albumIds)";

	public AlbumDao() {
		// TODO Auto-generated constructor stub
	}	
	
	public boolean verifyAccountAlbum(int accountId, Long[] albumIds){
		LogUtil.log("Card verifyAccountAlbum", Level.INFO, null);
		boolean success = false;
		try {
			Query query = entityManager.createQuery(verifyCreateCardHql);
			query.setParameter("accountId", accountId);
			query.setParameter("albumIds", Arrays.asList(albumIds));
			Long count = (Long) query.getSingleResult();
			success = count == albumIds.length;
			LogUtil.log("verifyAccountAlbum successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("verifyAccountAlbum failed", Level.SEVERE, re);
			throw re;
		}
		return success;
	}
	
    private static final String followAlbumJPQL = "select follower from Follower follower where follower.id.followingId = ?1 and follower.id.followedId in (select album.account from Album album where album.albumId = ?2)";
	
	public Follower getFollower(Integer followingId, Long albumId) {
		LogUtil.log("getFollowering", Level.INFO, null);
		Follower follower = null;
		try {
			Query query = entityManager.createQuery(followAlbumJPQL);
			query.setParameter(1, followingId);
			query.setParameter(2, albumId);
			follower = (Follower)query.getSingleResult();
			LogUtil.log("getFollower successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("getFollower failed", Level.SEVERE, re);
			throw re;
		}
		return follower;
	}

}
