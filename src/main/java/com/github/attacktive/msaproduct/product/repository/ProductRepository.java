package com.github.attacktive.msaproduct.product.repository;

import java.util.List;
import java.util.Optional;

import com.github.attacktive.msaproduct.product.api.request.ProductRequest;
import com.github.attacktive.msaproduct.product.domain.Product;
import org.springframework.lang.Nullable;

public interface ProductRepository {
	Optional<Product> findById(long id);

	List<Product> findAll(@Nullable Integer page, @Nullable Integer size);

	Product save(ProductRequest productRequest);

	void deleteById(long id);
}
