package com.scoutin.application.interfaces;

import javax.ejb.Remote;

import com.scoutin.entities.Album;

@Remote
public interface AlbumBeanRemote {

	/*
	 * @param accountId:(Integer) must be existent
	 * @param album:(Album) must be not-null
	 * @return created Album if successful
	 * @throws Throwable if failed
	 */
	public Album createAlbum(Integer accountId, Album album);
}
