package com.scoutin.daos;

import java.util.Arrays;
import java.util.List;
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
	
    private static final String getAccountIdsInClusterJPQL = "select ac.id.accountId from AccountCluster ac where ac.cluster.account.accountId = :accountId and ac.id.clusterId in (:clusterIds)";
	
	public List<Integer> getAccountIdsInCluster(Long[] clusterIds, int accountId) {
		LogUtil.log("getAccountIdsInClustering", Level.INFO, null);
		try {
			Query query = entityManager.createQuery(getAccountIdsInClusterJPQL);
			query.setParameter("accountId", accountId);
			query.setParameter("clusterIds", Arrays.asList(clusterIds));
			List<Integer> results = (List<Integer>)query.getResultList();
			LogUtil.log("getAccountIdsInCluster successful", Level.INFO, null);
			return results;
		} catch (RuntimeException re) {
			LogUtil.log("getAccountIdsInCluster failed", Level.SEVERE, re);
			throw re;
		}	
	}
	
	
}
