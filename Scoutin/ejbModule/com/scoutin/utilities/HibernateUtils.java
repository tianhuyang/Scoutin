package com.scoutin.utilities;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.scoutin.daos.*;

public class HibernateUtils {

	private static final Log log = LogFactory.getLog(AlbumHome.class);
	public static final SessionFactory sessionFactory = getSessionFactory();
	protected static SessionFactory getSessionFactory() {
		try {
			Configuration config = new Configuration().configure();
			return config.buildSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI"+e.getMessage());
		}
	}
	public static final AccountHome accountHome = new AccountHome();
	public static final CardHome cardHome = new CardHome();
}
