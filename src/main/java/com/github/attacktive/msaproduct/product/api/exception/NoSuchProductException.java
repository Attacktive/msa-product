package com.github.attacktive.msaproduct.product.api.exception;

public class NoSuchProductException extends RuntimeException {
	public NoSuchProductException(long id) {
		super("The product with id %d does not exist.".formatted(id));
	}
}
