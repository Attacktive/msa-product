package com.github.attacktive.msaproduct.product.domain;

import lombok.Builder;

@Builder
public record Product(long id, String name, String description, long price, long stock) {
	public Product withStockChange(long stockChange) {
		var stock = this.stock + stockChange;
		if (stock < 0) {
			throw new InsufficientStockException(id, stock, stockChange);
		}

		return Product.builder()
			.id(id)
			.name(name)
			.description(description)
			.price(price)
			.stock(stock)
			.build();
	}
}
