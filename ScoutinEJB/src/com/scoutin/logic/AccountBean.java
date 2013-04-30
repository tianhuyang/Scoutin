package com.scoutin.logic;

import java.util.Map;

import javax.ejb.*;

import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.interfaces.AccountBeanRemote;

/**
 * Session Bean implementation class AccountBean
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.NEVER)
public class AccountBean implements AccountBeanRemote {

	@EJB private AccountBeanService accountBeanService;
    /**
     * Default constructor. 
     */
    public AccountBean() {
        // TODO Auto-generated constructor stub
    }

	/*
	 * @see com.scoutin.logic.AccountBeanRemote#signup(Account account)
	 */
	@Override
	public Account signup(Account account) {
		account.setAccountId(null);
		return accountBeanService.signup(account);
	}

	/*
	 * @see com.scoutin.logic.AccountBeanRemote#createAlbum(Integer accountId, Album album)
	 */
	@Override
	public Album createAlbum(Integer accountId, Album album) {
		album.setAlbumId(null);
		album = accountBeanService.createAlbum(accountId,album);
		if(album != null){
			album.setAccount(null);
		}
		return album;
	}

}
