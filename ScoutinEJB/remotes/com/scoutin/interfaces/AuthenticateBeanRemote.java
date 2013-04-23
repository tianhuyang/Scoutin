package com.scoutin.interfaces;

import javax.ejb.Remote;

import com.scoutin.entities.Account;

@Remote
public interface AuthenticateBeanRemote {	
	public Account authenticate(String[] args, int type);
}
