package com.github.attacktive.msaproduct.product.adapter.inbound;

import java.util.List;
import java.util.Set;

import com.github.attacktive.msaproduct.product.adapter.outbound.ProductRepository;
import com.github.attacktive.msaproduct.product.application.exception.NoSuchProductException;
import com.github.attacktive.msaproduct.product.application.request.AddProductRequest;
import com.github.attacktive.msaproduct.product.application.request.UpdateProductRequest;
import com.github.attacktive.msaproduct.product.domain.Product;
import com.github.attacktive.msaproduct.product.port.inbound.ProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements ProductUseCase {
	private final ProductRepository productRepository;

	@Override
	public Product getProduct(long id) {
		return productRepository.findById(id)
			.orElseThrow(() -> new NoSuchProductException(id));
	}

	@Override
	public List<Product> getProductsByPagination(@Nullable Integer page, @Nullable Integer size, Set<Long> productIds) {
		return productRepository.findAll(page, size, productIds);
	}

	@Override
	public Product addProduct(AddProductRequest addProductRequest) {
		return productRepository.save(addProductRequest);
	}

	@Override
	public Product updateProduct(long id, UpdateProductRequest updateProductRequest) {
		var productId = productRepository.findById(id)
			.map(Product::id)
			.orElseThrow(() -> new NoSuchProductException(id));

		return productRepository.save(updateProductRequest.withId(productId));
	}

	@Override
	public void deleteProduct(long id) {
		productRepository.findById(id)
			.orElseThrow(() -> new NoSuchProductException(id));

		productRepository.deleteById(id);
	}
}
