package com.scoutin.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;

import com.scoutin.application.exception.ApplicationException;
import com.scoutin.application.interfaces.AlbumBeanRemote;
import com.scoutin.entities.Album;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
public class AlbumBean implements AlbumBeanRemote {

	@EJB
	private AlbumService albumService;

	/*
	 * @see com.scoutin.logic.AccountBeanRemote#createAlbum(Integer accountId,
	 * Album album)
	 */
	@Override
	public Album createAlbum(Integer accountId, Album album) {
		try {
			album = albumService.createAlbum(accountId, album);
			if (album != null) {
				album.setAccount(null);
			}
			return album;
		} catch (Throwable t) {
			throw new ApplicationException(t.getMessage());
		}

	}

	@Override
	public void blockAlbum(Integer followingAccountId, Long followedAlbumId, boolean blocked) {
		try {
			albumService.blockAlbum(followingAccountId, followedAlbumId, blocked);
		} catch (Throwable t) {
			if (t.getCause() instanceof EntityExistsException == false)
				throw new ApplicationException(t.getMessage());
		}
	}

}
