package com.scoutin.daos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.entities.AlbumHome;
import com.scoutin.utilities.CommonUtils;
import com.scoutin.utilities.DaoUtils;

public class AlbumDao extends AlbumHome {

	private static final Log log = LogFactory.getLog(AlbumDao.class);
	private final String verifyCreateCardHql = "select count(album) from Album album where album.account.accountId = :accountId and albumId in :albumIds";

	public AlbumDao() {
		// TODO Auto-generated constructor stub
	}	
	
	public boolean verifyAccountAlbum(int accountId, Long[] albumIds){
		log.debug("Card verifyAccountAlbum");
		boolean success = false;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(verifyCreateCardHql);
			query.setParameter("accountId", accountId);
			query.setParameterList("albumIds", albumIds);
			Long count = (Long) query.iterate().next();
			success = count == albumIds.length;
			log.debug("verifyAccountAlbum successful");
		} catch (RuntimeException re) {
			log.error("verifyAccountAlbum failed", re);
			throw re;
		}
		return success;
	}

}
