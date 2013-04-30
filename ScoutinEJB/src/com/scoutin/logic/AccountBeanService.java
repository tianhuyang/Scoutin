package com.scoutin.logic;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.beanutils.BeanUtils;

import com.scoutin.daos.AccountDao;
import com.scoutin.daos.AccountStatDao;
import com.scoutin.daos.AlbumDao;
import com.scoutin.daos.ProfileDao;
import com.scoutin.entities.Account;
import com.scoutin.entities.Accountstat;
import com.scoutin.entities.Album;
import com.scoutin.entities.Profile;

@Stateless
public class AccountBeanService {
	// @EJB DaoUtils daoUtils;
	@EJB
	private AccountDao accountDao;
	@EJB
	private AccountStatDao accountStatDao;
	@EJB
	private ProfileDao profileDao;
	@EJB
	private AlbumDao albumDao;

	/**
	 * Default constructor.
	 */
	public AccountBeanService() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * @see com.scoutin.logic.AccountBeanRemote#signup(Account account)
	 */
	public Account signup(Account account) {
		// save account		
		accountDao.save(account);
		// save accountStat
		Accountstat accountStat = new Accountstat();
		accountStat.setAccount(account);
		accountStatDao.save(accountStat);
		// save profile
		Profile profile = new Profile();
		profile.setAccount(account);
		profileDao.save(profile);
		account.setProfile(profile);
		account.setAccountstat(accountStat);

		return account;
	}

	/* 
	 * @see com.scoutin.logic.AccountBeanRemote#createAlbum(Integer accountId, Album album)
	 */
	public Album createAlbum(Integer accountId, Album album) {
		Account account = accountDao.getReference(accountId);
		album.setAccount(account);		
		albumDao.save(album);
		return album;
	}
}
