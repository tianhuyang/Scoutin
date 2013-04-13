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

	private static final Log log = LogFactory.getLog(AlbumHome.class);
	private final String albumExistHql = "select count(a) from Album a where a.albumId in (:albumIds)";
	
	public AlbumDao() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean hasAll(long[] albumIds) {
		log.debug("album hasAll");
		boolean hasAll = false;
		Long[] lAlbumIds = new Long[albumIds.length];
		CommonUtils.longToLong(lAlbumIds, albumIds);
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession().createQuery(albumExistHql);
			query.setParameterList("albumIds", lAlbumIds);
            Long count = (Long)query.iterate().next();
            hasAll = count == albumIds.length; 
			log.debug("hasAll successful");
		} catch (RuntimeException re) {
			log.error("hasAll failed", re);
			throw re;
		}
		return hasAll;
	}

}
