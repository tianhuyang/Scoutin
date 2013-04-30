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
			album = null;
		}
		if (album == null) {
			throw new ScoutinException(
					ScoutinException.Album_CreateAlbum_Failure_Status,
					ScoutinException.Album_CreateAlbum_Failure_Message);
		}
		return album;
	}
}
