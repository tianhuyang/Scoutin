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
	private CardRepostDao cardRepostDao;
	@EJB
	private CardLikeDao cardLikeDao;
	@EJB
	private CommentDao commentDao;
	

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

	public CardRepostDao getCardRepostDao() {
		return cardRepostDao;
	}
	
	public CardLikeDao getCardLikeDao() {
		return cardLikeDao;
	}
	
	public CommentDao getCommentDao() {
		return commentDao;
	}
}
