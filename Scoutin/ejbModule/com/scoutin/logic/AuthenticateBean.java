package com.scoutin.logic;

import java.util.TreeSet;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.scoutin.entities.Account;
import com.scoutin.entities.AlbumcardId;
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
		switch (type) {
		case AccountConstants.AuthenticateTypeEmail:
			account = DaoUtils.accountDao.authenticateWithEmail(args[0],
					args[1]);
			if (account != null) {
				TreeSet<String> getFields = new TreeSet<String>();
				getFields.add("profile");
				getFields.add("albums");
				getFields.add("accountstat");
				DaoUtils.accountDao.getAndRemoveProxies(account, getFields);
			}
			break;
		}
		return account;
	}

}
