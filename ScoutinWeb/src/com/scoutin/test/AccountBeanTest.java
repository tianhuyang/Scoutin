package com.scoutin.test;

import com.scoutin.entities.Account;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.AccountBeanRemote;
import com.scoutin.logic.AccountService;
import com.scoutin.logic.AuthenticateBeanRemote;
import com.scoutin.utilities.EJBUtils;

public class AccountBeanTest {

	/**
	 * @param args
	 */
	public static String signupPath = "ScoutinApplication/Scoutin/AccountBean!";
	public static String authenticatePath = "ScoutinApplication/Scoutin/AuthenticateBean!";
	public static String  path = "";
	public static void testSignup(){
		String[] args={"tianhuyang@hotmail.com","password"};
		try {
			int id = AccountService.signup(args, Account.AuthenticateTypeEmail);
			System.out.println(id);
		} catch (ScoutinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void testAuthenticate(){
		String args[] = new String[2];
		args[0] = "tianhuyang@gmail.com";
		args[1] = "password1";
		try {
			System.out.println(AccountService.authenticate(args, Account.AuthenticateTypeEmail));
		} catch (ScoutinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testSignup();
		testAuthenticate();
	}

}
