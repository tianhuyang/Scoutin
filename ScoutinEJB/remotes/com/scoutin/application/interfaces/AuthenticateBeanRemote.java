package com.scoutin.application.interfaces;

import javax.ejb.Remote;

import com.scoutin.entities.Account;

@Remote
public interface AuthenticateBeanRemote {	
	/*
	 * @param args:(String[]) must be not null
	 * @param args:(int) type be existent
	 * @return account if successful otherwise null or throws Throwable
	 */
	public Account authenticate(String[] args, int type);
}
