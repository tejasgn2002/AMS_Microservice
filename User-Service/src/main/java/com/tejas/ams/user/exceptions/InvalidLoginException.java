package com.tejas.ams.user.exceptions;

public class InvalidLoginException extends RuntimeException{
	public InvalidLoginException(String message) {
		super(message);
	}
}
