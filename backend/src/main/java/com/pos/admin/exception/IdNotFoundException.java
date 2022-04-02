package com.pos.admin.exception;

public class IdNotFoundException extends RuntimeException{
	public IdNotFoundException(String msg) {
		super(msg);
	}
}