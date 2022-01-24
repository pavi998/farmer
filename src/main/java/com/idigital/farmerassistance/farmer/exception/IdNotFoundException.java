package com.idigital.farmerassistance.farmer.exception;

public class IdNotFoundException extends RuntimeException {
	private static final long serialVersionUID=1L;
	
	public IdNotFoundException(String str) {
		super(str);
	}
}

