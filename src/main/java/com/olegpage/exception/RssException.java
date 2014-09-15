package com.olegpage.exception;

public class RssException extends Exception{

	/*public RssException(String msg, Throwable cause) {
		super(msg, cause);
	}*/
	public RssException(Throwable cause) {
		super(cause);
	}

}
