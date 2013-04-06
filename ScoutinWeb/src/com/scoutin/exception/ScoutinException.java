package com.scoutin.exception;

public class ScoutinException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5951162812583275078L;
	public static final int Account_Signup_Failure_Code = 1;
	public static final String Account_Signup_Failure_Text = "account.signup.failure";
	public static final int Account_Signin_Failure_Code = 1;
	public static final String Account_Signin_Failure_Text = "account.signin.failure";
	
	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public ScoutinException() {
		// TODO Auto-generated constructor stub
	}

	public ScoutinException(int code,String text) {
		super(text);
		this.code=code;
		// TODO Auto-generated constructor stub
	}

	public ScoutinException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ScoutinException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public ScoutinException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
