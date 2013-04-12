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
import com.scoutin.utilities.DaoUtils;

/**
 * Session Bean implementation class AccountBean
 */
@Stateless
@LocalBean
public class AccountBean implements AccountBeanRemote {

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
			DaoUtils.accountDAO.persist(account);

			
		} catch (IllegalAccessException e) {
			account = null;
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			account = null;
			//e.printStackTrace();
		}
		
		return account;
	}

	/*
	 * must have correct not-null accountId
	 * @see com.scoutin.logic.AccountBeanRemote#createAlbum(java.util.Map)
	 */
	@Override
	public Album createAlbum(Map<String, Object> properties) {
		// TODO Auto-generated method stub
		Integer accountId = (Integer)properties.get("accountId");
		Account account = DaoUtils.accountDAO.load(accountId);
		Album album = new Album();
		album.setAccount(account);
		try {
			BeanUtils.populate(album, properties);
			//BeanUtils.populate(account, properties);
			DaoUtils.albumDao.persist(album);
			DaoUtils.albumDao.evict(album);
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
