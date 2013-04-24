package com.scoutin.exception;

public interface ScoutinError {
	public static final int OK_Status=0;
	public static final String OK_Message = "success";
	/* 
	 * account begins
	 */
	public static final int Account_Signup_Input_Status = 1;
	public static final int Account_Signup_Failure_Status = 2;	
	public static final String Account_Signup_Input_Message = "error.account.signup.input";
	public static final String Account_Signup_Failure_Message = "error.account.signup.failure";
	
	public static final int Account_No_Signin_Status = 7;
	public static final String Account_No_Signin_Message = "error.no.signin.failure";
	
	public static final int Account_Signin_Input_Status = 100;
	public static final int Account_Signin_Failure_Status = 101;
	public static final String Account_Signin_Input_Message = "error.account.signin.input";
	public static final String Account_Signin_Failure_Message = "error.account.signin.failure";
	/* 
	 * account ends
	 */
	
	/* 
	 * card begins
	 */
	public static final int Card_CreateCard_Input_Status = 10000;
	public static final String Card_CreateCard_Input_Message = "error.card.createcard.input";
	public static final int Card_CreateCard_Failure_Status = 10001;
	public static final String Card_CreateCard_Failure_Message = "error.card.createcard.failure";
	public static final int Card_CreateComment_Input_Status = 10002;
	public static final String Card_CreateComment_Input_Message = "error.card.createcomment.input";
	public static final int Card_CreateComment_Failure_Status = 10003;
	public static final String Card_CreateComment_Failure_Message = "error.card.createcomment.failure";
	public static final int Card_RepostCard_Input_Status = 10004;
	public static final String Card_RepostCard_Input_Message = "error.card.repostcard.input";
	public static final int Card_RepostCard_Failure_Status = 10005;
	public static final String Card_RepostCard_Failure_Message = "error.card.repostcard.failure";
	public static final int Card_LikeCard_Input_Status = 10006;
	public static final String Card_LikeCard_Input_Message = "error.card.likecard.input";
	public static final int Card_LikeCard_Failure_Status = 10007;
	public static final String Card_LikeCard_Failure_Message = "error.card.likecard.failure";
	/* 
	 * card ends
	 */
	
	/* 
	 * album begins
	 */
	public static final int Album_CreateAlbum_Input_Status = 20000;
	public static final String Album_CreateAlbum_Input_Message = "error.album.createalbum.input";
	public static final int Album_CreateAlbum_Failure_Status = 20001;
	public static final String Album_CreateAlbum_Failure_Message = "error.album.createcard.failure";
	/* 
	 * card ends
	 */
}
