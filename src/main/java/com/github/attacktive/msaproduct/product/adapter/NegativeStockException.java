package com.github.attacktive.msaproduct.product.adapter;

public class NegativeStockException extends GenericBadRequestException {
	public NegativeStockException() {
		super("The request can't be processed because that's going to end up with negative stock.");
	}
}
