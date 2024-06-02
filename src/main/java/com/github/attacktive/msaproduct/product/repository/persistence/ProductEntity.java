package com.github.attacktive.msaproduct.product.repository.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.attacktive.msaproduct.product.api.request.ProductRequest;
import com.github.attacktive.msaproduct.product.domain.Product;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String description;

	private long price;

	public ProductEntity(ProductRequest productRequest) {
		id = productRequest.id();
		name = productRequest.name();
		description = productRequest.description();
		price = productRequest.price();
	}

	public Product toProduct() {
		return Product.builder()
			.id(id)
			.name(name)
			.description(description)
			.price(price)
			.build();
	}
}
