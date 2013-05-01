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

	/*
	 * @param followingAccountId:(Integer) must be existent
	 * @param followedAlbumId:(Long) must be existent
	 * @return whether the card is followed or not followed
	 * @throws ApplicationException if failed
	 * followingAccountId must follow the account which followedAlbumId belongs to
	 */
	public boolean blockAlbum(Integer followingAccountId, Long followedAlbumId);
}
