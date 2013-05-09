package com.scoutin.daos;

import java.util.Arrays;
import java.util.logging.Level;


import javax.ejb.Stateless;
import javax.persistence.Query;

import com.scoutin.entities.Account;
import com.scoutin.facades.AccountFacade;
import com.scoutin.facades.LogUtil;
@Stateless
public class AccountDao extends AccountFacade {

	private static final String emailAuthJPQL = "select account from Account account where account.email = ?1 and account.password = ?2";
	
	public Account authenticateWithEmail(String email, String password) {
		LogUtil.log("authenticate with email", Level.INFO, null);
		Account account = null;
		try {
			Query query = entityManager.createQuery(emailAuthJPQL);
			query.setParameter(1, email);
			query.setParameter(2, password);
			account = (Account)query.getSingleResult();
			LogUtil.log("authenticate successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("authenticate failed", Level.SEVERE, re);
			throw re;
		}
		return account;
	}
	
    private static final String clustersBelongToAccountHql = "select count(cluster.clusterId) from Cluster cluster where cluster.account.accountId = :accountId and cluster.clusterId in (:clusterIds)";
	
	public boolean clustersBelongToAccount(Long[] clusterIds, int accountId) {
		LogUtil.log("clustersBelongToAccounting", Level.INFO, null);
		try {
			Query query = entityManager.createQuery(clustersBelongToAccountHql);
			query.setParameter("accountId", accountId);
			query.setParameter("clusterIds", Arrays.asList(clusterIds));
			long count = (Long)query.getSingleResult();
			LogUtil.log("clustersBelongToAccount successful", Level.INFO, null);
			return count == clusterIds.length;
		} catch (RuntimeException re) {
			LogUtil.log("clustersBelongToAccount failed", Level.SEVERE, re);
			throw re;
		}	
	}
	
	
}
