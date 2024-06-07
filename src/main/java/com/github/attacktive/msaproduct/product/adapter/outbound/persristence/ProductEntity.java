package com.github.attacktive.msaproduct.product.adapter.outbound.persristence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.attacktive.msaproduct.product.adapter.inbound.ProductRequest;
import com.github.attacktive.msaproduct.product.domain.Product;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private long price;

	private long stock;

	public ProductEntity(ProductRequest productRequest) {
		id = productRequest.id();
		name = productRequest.name();
		description = productRequest.description();
		price = productRequest.price();
		stock = productRequest.stock();
	}

	public Product toProduct() {
		return Product.builder()
			.id(id)
			.name(name)
			.description(description)
			.price(price)
			.stock(stock)
			.build();
	}
}
