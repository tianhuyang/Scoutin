package com.scoutin.exception;

public class ScoutinException extends Exception implements ScoutinError{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5951162812583275078L;	
	
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ScoutinException() {
		// TODO Auto-generated constructor stub
	}

	public ScoutinException(int status,String message) {
		super(message);
		this.status=status;
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
	
	public String toString(){
		return status + ": "+ this.getMessage();
	}	
	

}
