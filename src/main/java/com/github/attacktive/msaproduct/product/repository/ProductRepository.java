package com.github.attacktive.msaproduct.product.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.github.attacktive.msaproduct.product.api.request.ProductRequest;
import com.github.attacktive.msaproduct.product.domain.Product;
import org.springframework.lang.Nullable;

public interface ProductRepository {
	Optional<Product> findById(long id);

	List<Product> findAll(@Nullable Integer page, @Nullable Integer size, @Nullable Set<Long> productIds);

	Product save(ProductRequest productRequest);

	void deleteById(long id);
}
