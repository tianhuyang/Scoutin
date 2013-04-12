package com.scoutin.daos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.entities.AlbumHome;
import com.scoutin.utilities.DaoUtils;

public class AlbumDao extends AlbumHome {

	private static final Log log = LogFactory.getLog(AlbumHome.class);
	
	public AlbumDao() {
		// TODO Auto-generated constructor stub
	}
	
	public void evict(Album instance) {
		log.debug("evicting instance");
		try {
			DaoUtils.sessionFactory.getCurrentSession().evict(instance);
			log.debug("evicting successful");
		} catch (RuntimeException re) {
			log.error("evicting failed", re);
			throw re;
		}
	}

}
