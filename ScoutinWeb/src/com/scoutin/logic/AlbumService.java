package com.scoutin.logic;

import com.scoutin.entities.Album;
import com.scoutin.exception.ScoutinException;
import com.scoutin.utilities.EJBUtils;

public class AlbumService {

	/*
	 * @param accountId:(Integer) must be existent and authenticated
	 * @param album:(Album) must be not null
	 * @return Album if successful otherwise throws ScoutinException 
	 */
	public static Album createAlbum(Integer accountId, Album album)
			throws ScoutinException {
		if (accountId == null || album == null){
			throw new IllegalArgumentException("null arguments");
		}	
		try {
			album = EJBUtils.albumBeanRemote.createAlbum(accountId, album);
		} catch (Throwable re) {
			throw new ScoutinException(
					ScoutinException.Album_CreateAlbum_Failure_Status,
					ScoutinException.Album_CreateAlbum_Failure_Message);
		}
		return album;
	}
	
	/*
	 * @param followingAccountId:(Integer) must be existent
	 * @param followedAlbumId:(Long) must be existent
	 * @return whether the card is followed or not followed
	 * @throws ApplicationException if failed
	 * followingAccountId must follow the account which followedAlbumId belongs to
	 */
	public static boolean followAlbum(Integer followingAccountId, Long followedAlbumId)throws ScoutinException{
		if (followingAccountId == null || followedAlbumId == null){
			throw new IllegalArgumentException("null arguments");
		}
		try {
			return EJBUtils.albumBeanRemote.followAlbum(followingAccountId, followedAlbumId);
		} catch (Throwable re) {
			throw new ScoutinException(
					ScoutinException.Album_FollowAlbum_Failure_Status,
					ScoutinException.Album_FollowAlbum_Failure_Message);
		}
	}
}
