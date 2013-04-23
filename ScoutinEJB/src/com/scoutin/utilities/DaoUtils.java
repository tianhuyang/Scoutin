package com.scoutin.utilities;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.scoutin.daos.*;

@Startup
@Singleton
public class DaoUtils {

	@EJB
	private AccountDao accountDao;
	@EJB
	private CardDao cardDao;
	@EJB
	private CardBodyDao cardBodyDao;
	@EJB
	private ProfileDao profileDao;
	@EJB
	private AlbumDao albumDao;
	@EJB
	private AccountStatDao accountStatDao;
	@EJB
	private AlbumcardDao albumcardDao;
	@EJB
	private CardRepostDao cardRepostDao;

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public CardDao getCardDao() {
		return cardDao;
	}

	public CardBodyDao getCardBodyDao() {
		return cardBodyDao;
	}

	public ProfileDao getProfileDao() {
		return profileDao;
	}

	public AlbumDao getAlbumDao() {
		return albumDao;
	}

	public AccountStatDao getAccountStatDao() {
		return accountStatDao;
	}

	public AlbumcardDao getAlbumcardDao() {
		return albumcardDao;
	}

	public CardRepostDao getCardRepostDao() {
		return cardRepostDao;
	}
}
