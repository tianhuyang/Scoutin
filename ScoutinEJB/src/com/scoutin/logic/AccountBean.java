package com.scoutin.logic;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.*;

import org.apache.commons.beanutils.BeanUtils;

import com.scoutin.entities.Account;
import com.scoutin.entities.Accountstat;
import com.scoutin.entities.Album;
import com.scoutin.entities.Profile;
import com.scoutin.interfaces.AccountBeanRemote;
import com.scoutin.utilities.DaoUtils;

/**
 * Session Bean implementation class AccountBean
 */
@Stateless
@LocalBean
public class AccountBean implements AccountBeanRemote {

	@EJB DaoUtils daoUtils;
    /**
     * Default constructor. 
     */
    public AccountBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Account signup(Map<String,Object> properties) {
		// TODO Auto-generated method stub
		Account account = new Account();
		try {
			BeanUtils.populate(account, properties);
			//insert values
			Accountstat accountStat = new Accountstat();
			accountStat.setAccount(account);
			daoUtils.getAccountStatDao().save(accountStat);
			Profile profile = new Profile();
			profile.setAccount(account);
			daoUtils.getProfileDao().save(profile);
			// set return fields
			daoUtils.getAccountDao().detach(account);
			account.setProfile(profile);
			account.setAccountstat(accountStat);
			
		} catch (IllegalAccessException e) {
			account = null;
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			account = null;
			e.printStackTrace();
		}
		
		return account;
	}

	/*
	 * must have not-null properties, correct not-null accountId:int
	 * @see com.scoutin.logic.AccountBeanRemote#createAlbum(java.util.Map)
	 */
	@Override
	public Album createAlbum(Map<String, Object> properties) {
		// TODO Auto-generated method stub
		Integer accountId = (Integer)properties.get("accountId");
		Account account = daoUtils.getAccountDao().findById(accountId);
		Album album = new Album();
		album.setAccount(account);
		try {
			BeanUtils.populate(album, properties);
			daoUtils.getAlbumDao().save(album);
			daoUtils.getAlbumDao().detach(album);
			album.setAccount(null);
		} catch (IllegalAccessException e) {
			album = null;
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			album = null;
			//e.printStackTrace();
		}
		return album;
	}

}