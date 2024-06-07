package com.github.attacktive.msaproduct.product.adapter;

public class ProductInUseException extends IllegalStateException {
	public ProductInUseException(long id) {
		super("Product %d is already in use.".formatted(id));
	}
}
