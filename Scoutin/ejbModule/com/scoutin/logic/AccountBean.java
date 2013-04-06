package com.scoutin.logic;

import javax.annotation.Resource;
import javax.ejb.*;

import com.scoutin.entities.Account;
import com.scoutin.utilities.HibernateUtils;
import com.scoutin.daos.*;

/**
 * Session Bean implementation class AccountBean
 */
@Stateless
@LocalBean
public class AccountBean implements AccountBeanRemote {

    /**
     * Default constructor. 
     */
    public AccountBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public int signup(Account account) {
		// TODO Auto-generated method stub
		int ret = -1;
		HibernateUtils.accountHome.attachDirty(account);
		ret = account.getAccountId();
		return ret;
	}

}
