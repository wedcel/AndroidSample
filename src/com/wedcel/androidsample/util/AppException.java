package com.wedcel.androidsample.util;



public class AppException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorMsg;
	
	
	public AppException(String errCode, String errMsg) {
		this.errorCode=errCode;
		this.errorMsg=errMsg;
	}


	public AppException(String errCode,String errorStr, Throwable e) {
		super(e);
		this.errorCode=errCode;
		this.errorMsg=errorStr;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	public String getErrorMsg() {
		return errorMsg;
	}


	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
