package com.scoutin.utilities;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.scoutin.daos.*;

@Startup
@Singleton
public class DaoUtils {

	 @EJB public  AccountDao accountDao;
	 @EJB public  CardDao cardDao;
	 @EJB public  CardBodyDao cardBodyDao;
	 @EJB public  ProfileDao profileDao;
	 @EJB public  AlbumDao albumDao;
	 @EJB public  AccountStatDao accountStatDao;
	 @EJB public  AlbumcardDao albumcardDao;
	 @EJB public  CardRepostsDao cardRepostsDato;
	 public AccountDao getAccountDao(){
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
	public CardRepostsDao getCardRepostsDato() {
		return cardRepostsDato;
	}
}
