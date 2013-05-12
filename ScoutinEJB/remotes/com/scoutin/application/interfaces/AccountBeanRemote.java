package com.scoutin.application.interfaces;

import javax.ejb.Remote;
import com.scoutin.entities.*;

@Remote
public interface AccountBeanRemote {
	
	/*
	 * @param account:(Account) must be not-null, null account.accountId, null account.version
	 * @return created Account if successful
	 * @throws ApplicationException if failed
	 */
	public Account signup(Account account);
	
	/*
	 * @param followingAccountId:(Integer) must be existent
	 * @param followedAccountId:(Integer) must be existent
	 * @param followed:(boolean)
	 * @throws ApplicationException if failed
	 */
	public void followAccount(Integer followingAccountId, Integer followedAccountId, boolean followed);
	
}
