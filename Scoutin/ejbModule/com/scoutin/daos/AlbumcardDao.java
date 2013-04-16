package com.scoutin.daos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.scoutin.entities.AlbumHome;
import com.scoutin.entities.AlbumcardHome;
import com.scoutin.entities.AlbumcardId;
import com.scoutin.utilities.CommonUtils;
import com.scoutin.utilities.DaoUtils;

public class AlbumcardDao extends AlbumcardHome {

	private static final Log log = LogFactory.getLog(AlbumcardHome.class);
	public AlbumcardDao() {
		// TODO Auto-generated constructor stub
	}

//    private final String albumExistHql = "select count(ac) from Albumcard ac where ac.id in :albumcardIds";
//	
//	public boolean hasAll(AlbumcardId[] albumIds) {
//		log.debug("albumcard hasAll");
//		boolean hasAll = false;
//		try {
//			Query query = DaoUtils.sessionFactory.getCurrentSession().createQuery(albumExistHql);
//			query.setParameterList("albumcardIds", albumIds);
//            Long count = (Long)query.iterate().next();
//            hasAll = count == albumIds.length; 
//			log.debug("hasAll successful");
//		} catch (RuntimeException re) {
//			log.error("hasAll failed", re);
//			throw re;
//		}
//		return hasAll;
//	}
}
