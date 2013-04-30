package com.scoutin.interfaces;

import javax.ejb.Remote;
import com.scoutin.entities.*;

@Remote
public interface AccountBeanRemote {
	
	/*
	 * @param account:(Account) must be not-null
	 * @return created Account if successful otherwise null or throws Throwable
	 */
	public Account signup(Account account);
	/*
	 * @param accountId:(Integer) must be existent
	 * @param album:(Album) must be not-null
	 * @see com.scoutin.logic.AccountBeanRemote#createAlbum(Integer accountId, Album album)
	 * @return created Album if successful otherwise null or throws Throwable
	 */
	public Album createAlbum(Integer accountId, Album album);
}
