package com.scoutin.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.scoutin.daos.AccountDao;
import com.scoutin.daos.AlbumDao;
import com.scoutin.daos.BlockedAlbumDao;
import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.entities.BlockedAlbum;
import com.scoutin.entities.BlockedAlbumId;
import com.scoutin.entities.Follower;

@Stateless
public class AlbumBeanService {

	@EJB private AccountDao accountDao;
	@EJB private AlbumDao albumDao;
	@EJB private BlockedAlbumDao blockedAlbumDao;
	/* 
	 * @see com.scoutin.logic.AccountBeanRemote#createAlbum(Integer accountId, Album album)
	 */
	public Album createAlbum(Integer accountId, Album album) {
		Account account = accountDao.getReference(accountId);
		album.setAccount(account);		
		albumDao.save(album);
		return album;
	}
	
	public boolean followAlbum(Integer followingAccountId, Long followedAlbumId) {
		// TODO Auto-generated method stub
		Follower follower = albumDao.getFollower(followingAccountId, followedAlbumId);
		BlockedAlbumId blockedalbumId = new BlockedAlbumId(followedAlbumId,follower.getId().getFollowedId(),follower.getId().getFollowingId());
		BlockedAlbum blockedalbum = blockedAlbumDao.findById(blockedalbumId);
		if(blockedalbum == null){
			blockedalbum = new BlockedAlbum();
			blockedalbum.setId(blockedalbumId);
			blockedAlbumDao.save(blockedalbum);
			return true;
		}
		else{
			blockedAlbumDao.removeById(blockedalbumId);
			return false;
		}
		
	}
	
}
