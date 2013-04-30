package com.scoutin.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.scoutin.application.exception.ApplicationException;
import com.scoutin.application.interfaces.AlbumBeanRemote;
import com.scoutin.entities.Album;

@Stateless
public class AlbumBean implements AlbumBeanRemote {

	@EJB
	private AlbumBeanService albumBeanService;

	/*
	 * @see com.scoutin.logic.AccountBeanRemote#createAlbum(Integer accountId,
	 * Album album)
	 */
	@Override
	public Album createAlbum(Integer accountId, Album album) {
		try {
			album.setAlbumId(null);
			album = albumBeanService.createAlbum(accountId, album);
			if (album != null) {
				album.setAccount(null);
			}
			return album;
		} catch (Throwable t) {
			throw new ApplicationException(t.getMessage());
		}

	}
}
