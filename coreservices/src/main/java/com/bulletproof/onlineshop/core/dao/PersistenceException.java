package com.bulletproof.onlineshop.core.dao;

public class PersistenceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersistenceException() {
		
	}
	
	public PersistenceException(String message) {
		super(message);
	}
	
	public PersistenceException(String message, Exception e) {
		super(message, e);
	}
}
