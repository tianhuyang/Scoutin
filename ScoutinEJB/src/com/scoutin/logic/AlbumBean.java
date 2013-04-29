package com.scoutin.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.scoutin.entities.Album;
import com.scoutin.interfaces.AlbumBeanRemote;

@Stateless
public class AlbumBean implements AlbumBeanRemote {
	
	@EJB private AlbumBeanService albumBeanService;
	/*
	 * @see com.scoutin.logic.AccountBeanRemote#createAlbum(Integer accountId, Album album)
	 */
	@Override
	public Album createAlbum(Integer accountId, Album album) {
		album.setAlbumId(null);
		album = albumBeanService.createAlbum(accountId,album);
		if(album != null){
			album.setAccount(null);
		}
		return album;
	}
}
