package com.github.attacktive.msaproduct.product.domain;

public class InsufficientStockException extends RuntimeException {
	public InsufficientStockException(long productId, long currentStock, long stockChange) {
		super("The product #%d has insufficient stock; current stock: %d, requested stock change: %d".formatted(productId, currentStock, stockChange));
	}
}
