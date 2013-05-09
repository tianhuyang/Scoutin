package com.scoutin.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.scoutin.daos.AccountDao;
import com.scoutin.daos.AccountStatDao;
import com.scoutin.daos.FollowerDao;
import com.scoutin.daos.ProfileDao;
import com.scoutin.entities.Account;
import com.scoutin.entities.AccountStat;
import com.scoutin.entities.Follower;
import com.scoutin.entities.FollowerId;
import com.scoutin.entities.Profile;

@Stateless
public class AccountBeanService {
	// @EJB DaoUtils daoUtils;
	@EJB
	private AccountDao accountDao;
	@EJB
	private AccountStatDao accountStatDao;
	@EJB
	private ProfileDao profileDao;
	@EJB
	private FollowerDao followerDao;

	/**
	 * Default constructor.
	 */
	public AccountBeanService() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * @see com.scoutin.logic.AccountBeanRemote#signup(Account account)
	 */
	public Account signup(Account account) {
		// save account		
		accountDao.save(account);
		// save accountStat
		AccountStat accountStat = new AccountStat();
		accountStat.setAccount(account);
		accountStatDao.save(accountStat);
		// save profile
		Profile profile = new Profile();
		profile.setAccount(account);
		profileDao.save(profile);
		account.setProfile(profile);
		account.setAccountStat(accountStat);

		return account;
	}
	
	/*
	 * @see com.scoutin.logic.AccountBeanRemote#followAccount(Integer followingAccountId,Integer followedAccountId)
	 */
	public boolean followAccount(Integer followingAccountId, Integer followedAccountId) {
		FollowerId followerId = new FollowerId(followedAccountId, followingAccountId);
		Follower follower = followerDao.findById(followerId);
		if(follower == null){
			follower = new Follower();
			follower.setId(followerId);
			followerDao.save(follower);
			accountStatDao.increaseFollowersCount(followedAccountId, 1);
			accountStatDao.increaseFollowingCount(followingAccountId, 1);
			return true;
		}
		else{
			followerDao.removeById(followerId);
			accountStatDao.increaseFollowersCount(followedAccountId, -1);
			accountStatDao.increaseFollowingCount(followingAccountId, -1);
			return false;
		}	
		
	}

}
