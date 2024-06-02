package com.github.attacktive.msaproduct.product.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.github.attacktive.msaproduct.product.api.request.ProductRequest;
import com.github.attacktive.msaproduct.product.domain.Product;
import com.github.attacktive.msaproduct.product.repository.persistence.ProductEntity;
import com.github.attacktive.msaproduct.product.repository.persistence.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
	private static final int DEFAULT_PAGE = 1;
	private static final int DEFAULT_PAGE_SIZE = 20;

	private final ProductJpaRepository productJpaRepository;

	@Override
	@Transactional(readOnly = true)
	public Optional<Product> findById(long id) {
		return productJpaRepository.findById(id)
			.map(ProductEntity::toProduct);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll(@Nullable Integer page, @Nullable Integer size, @Nullable Set<Long> productIds) {
		int nonNullPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE);
		int nonNullSize = Optional.ofNullable(size).orElse(DEFAULT_PAGE_SIZE);

		var pageRequest = PageRequest.of(nonNullPage - 1, nonNullSize);

		Page<ProductEntity> pageOfProducts;
		if (productIds == null || productIds.isEmpty()) {
			pageOfProducts = productJpaRepository.findAll(pageRequest);
		} else {
			pageOfProducts = productJpaRepository.findAllByIdIn(productIds, pageRequest);
		}

		return pageOfProducts.map(ProductEntity::toProduct)
			.getContent();
	}

	@Override
	@Transactional
	public Product save(ProductRequest productRequest) {
		return productJpaRepository.save(new ProductEntity(productRequest))
			.toProduct();
	}

	@Override
	@Transactional
	public void deleteById(long id) {
		productJpaRepository.deleteById(id);
	}
}
