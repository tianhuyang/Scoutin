package com.scoutin.logic;

import java.util.TreeSet;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.scoutin.daos.AccountDao;
import com.scoutin.entities.Account;
import com.scoutin.interfaces.AccountConstants;
import com.scoutin.interfaces.AuthenticateBeanRemote;
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
	private @EJB DaoUtils daoUtils;

	@Override
	public Account authenticate(String[] args, int type) {
		// TODO Auto-generated method stub
		Account account = null;
		switch (type) {
		case AccountConstants.AuthenticateTypeEmail:
			account = daoUtils.getAccountDao().authenticateWithEmail(args[0],
					args[1]);
			if (account != null) {
				TreeSet<String> getFields = new TreeSet<String>();
				getFields.add("profile");
				//getFields.add("albums");
				getFields.add("accountstat");
				//DaoUtils.accountDao.unproxy(account, getFields);
				//DaoUtils.accountDao.evict(account);
				//System.out.println();System.out.println();
				//System.out.println(account.getAlbums().size());
				//System.out.println();System.out.println();
				System.out.println(account.getAlbums().getClass().getName());
			}
			break;
		}
		return account;
	}

}
