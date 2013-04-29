package com.scoutin.interfaces;

import javax.ejb.Remote;
import com.scoutin.entities.*;

@Remote
public interface AccountBeanRemote {
	
	/*
	 * @param account:(Account) must be not-null
	 * @return created Account if successful otherwise null or throws Throwable
	 */
	public Account signup(Account account);
	
}
