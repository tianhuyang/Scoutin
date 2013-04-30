package com.scoutin.logic;

import java.util.TreeSet;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.scoutin.application.interfaces.AccountConstants;
import com.scoutin.daos.AccountDao;
import com.scoutin.entities.Account;
import com.scoutin.utilities.DaoUtils;

@Stateless
public class AuthenticateBeanService {
	
	private @EJB AccountDao accountDao;
	
	/**
	 * Default constructor.
	 */
	public AuthenticateBeanService() {
		// TODO Auto-generated constructor stub
	}	

	/*
	 * @see com.scoutin.logic.AuthenticateBeanRemote#authenticate(String[] args, int type)
	 */
	public Account authenticate(String[] args, int type) {
		Account account = null;
		switch (type) {
		case AccountConstants.AuthenticateTypeEmail:
			account = accountDao.authenticateWithEmail(args[0],
					args[1]);
			if (account != null) {
				TreeSet<String> getFields = new TreeSet<String>();
				getFields.add("profile");
				getFields.add("accountstat");
				System.out.println(account.getAlbums().getClass().getName());
			}
			break;
		}
		return account;
	}
}
