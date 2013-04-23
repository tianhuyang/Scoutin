package com.scoutin.exception;

public interface ScoutinError {
	public static final int OK_Status=0;
	public static final String OK_Message = "success";
	/* 
	 * account begins
	 */
	public static final int Account_Signup_Input_Status = 1;
	public static final int Account_Signup_Failure_Status = 2;	
	public static final int Account_CreateAlbum_Failure_Status = 3;
	public static final int Account_No_Signin_Status = 4;
	public static final String Account_Signup_Input_Message = "error.account.signup.input.error";
	public static final String Account_Signup_Failure_Message = "error.account.signup.failure";
	public static final String Account_CreateAlbum_Failure_Message = "error.account.createalbum.failure";
	public static final String Account_No_Signin_Message = "error.no.signin.failure";
	
	public static final int Account_Signin_Input_Error = 100;
	public static final int Account_Signin_Failure_Status = 101;	
	public static final String Account_Signin_Input_Message = "error.account.signin.input.error";
	public static final String Account_Signin_Failure_Message = "error.account.signin.failure";
	/* 
	 * account ends
	 */
	
	/* 
	 * card begins
	 */
	public static final int Card_CreateCard_Input_Status = 10000;
	public static final int Card_CreateCard_Failure_Status = 10001;
	public static final String Card_CreateCard_Input_Message = "error.card.createcard.input.error";
	public static final String Card_CreateCard_Failure_Message = "error.account.createcard.failure";
	/* 
	 * card ends
	 */
}
