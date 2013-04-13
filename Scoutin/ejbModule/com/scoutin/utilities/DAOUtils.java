package com.scoutin.utilities;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.scoutin.daos.*;

public class DaoUtils {

	private static final Log log = LogFactory.getLog(DaoUtils.class);
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
	public static final AccountDao accountDao = new AccountDao();
	public static final CardDao cardDao = new CardDao();
	public static final CardStatDao cardStatDao = new CardStatDao();
	public static final CardBodyDao cardBodyDao = new CardBodyDao();
	public static final ProfileDao profileDao = new ProfileDao();
	public static final AlbumDao albumDao = new AlbumDao();
	public static final AccountStatDao accountStatDao = new AccountStatDao();
	public static final AlbumcardDao albumcardDao = new AlbumcardDao();
	public static final CardRepostsDao cardRepostsDato = new CardRepostsDao();
}
