package com.github.attacktive.msaproduct.product.adapter;

public abstract class GenericBadRequestException extends RuntimeException {
	public GenericBadRequestException(String message) {
		super(message);
	}
}
