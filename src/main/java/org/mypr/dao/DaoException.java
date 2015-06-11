package org.mypr.dao;

public class DaoException extends Exception{
	private static final long serialVersionUID = 1L;

	public DaoException(String msg, Throwable cause) { 
		super(msg, cause);
	}
}
