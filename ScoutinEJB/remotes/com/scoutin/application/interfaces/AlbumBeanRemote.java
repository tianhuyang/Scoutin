package com.scoutin.application.interfaces;

import javax.ejb.Remote;

import com.scoutin.entities.Album;

@Remote
public interface AlbumBeanRemote {

	/*
	 * @param accountId:(Integer) must be existent
	 * @param album:(Album) must be not-null, null album.albumId, null album.version
	 * @return created Album if successful
	 * @throws Throwable if failed
	 */
	public Album createAlbum(Integer accountId, Album album);	

	/*
	 * @param followingAccountId:(Integer) must be existent
	 * @param followedAlbumId:(Long) must be existent
	 * @param blocked:(boolean)
	 * @throws ApplicationException if failed
	 * followingAccountId must follow the account which followedAlbumId belongs to
	 */
	public void blockAlbum(Integer followingAccountId, Long followedAlbumId, boolean blocked);
}
