package com.scoutin.logic;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.scoutin.entities.Account;
import com.scoutin.utilities.DaoUtils;

/**
 * Session Bean implementation class AuthenticateBean
 */
@Stateless
@LocalBean
public class AuthenticateBean implements AuthenticateBeanRemote {

    /**
     * Default constructor. 
     */
    public AuthenticateBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Account authenticate(String[] args, int type) {
		// TODO Auto-generated method stub
		Account account = null;
		switch(type){
		case AccountConstants.AuthenticateTypeEmail:
			account = DaoUtils.accountDao.authenticateWithEmail(args[0],args[1]);
			break;
		}		
		return account;
	}

}
