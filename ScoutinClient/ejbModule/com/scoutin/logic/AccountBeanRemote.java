package com.scoutin.logic;

import javax.ejb.Remote;
import com.scoutin.entities.*;

@Remote
public interface AccountBeanRemote {
	
	public int signup(Account account);
}
