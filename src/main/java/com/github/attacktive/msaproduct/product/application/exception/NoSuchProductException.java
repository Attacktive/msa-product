package com.github.attacktive.msaproduct.product.application.exception;

public class NoSuchProductException extends RuntimeException {
	public NoSuchProductException(long id) {
		super("The product with id %d does not exist.".formatted(id));
	}
}
