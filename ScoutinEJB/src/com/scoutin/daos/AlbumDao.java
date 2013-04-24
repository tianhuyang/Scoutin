package com.scoutin.daos;

import java.util.logging.Level;


import javax.ejb.Singleton;
import javax.persistence.Query;

import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.facades.AlbumFacade;
import com.scoutin.facades.LogUtil;
import com.scoutin.utilities.CommonUtils;
import com.scoutin.utilities.DaoUtils;
@Singleton
public class AlbumDao extends AlbumFacade {

	private final String verifyCreateCardHql = "select count(album) from Album album where album.account.accountId = :accountId and album.albumId in :albumIds";

	public AlbumDao() {
		// TODO Auto-generated constructor stub
	}	
	
	public boolean verifyAccountAlbum(int accountId, Long[] albumIds){
		LogUtil.log("Card verifyAccountAlbum", Level.INFO, null);
		boolean success = false;
		try {
			Query query = entityManager.createQuery(verifyCreateCardHql);
			query.setParameter("accountId", accountId);
			query.setParameter("albumIds", albumIds);
			Long count = (Long) query.getSingleResult();
			success = count == albumIds.length;
			LogUtil.log("verifyAccountAlbum successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("verifyAccountAlbum failed", Level.SEVERE, re);
			throw re;
		}
		return success;
	}

}
