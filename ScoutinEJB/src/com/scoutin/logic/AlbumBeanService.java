package com.scoutin.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.scoutin.daos.AccountDao;
import com.scoutin.daos.AlbumDao;
import com.scoutin.entities.Account;
import com.scoutin.entities.Album;

@Stateless
public class AlbumBeanService {

	@EJB private AccountDao accountDao;
	@EJB private AlbumDao albumDao;
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
