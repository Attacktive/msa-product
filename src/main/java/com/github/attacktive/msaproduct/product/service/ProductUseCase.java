package com.github.attacktive.msaproduct.product.service;

import java.util.List;

import com.github.attacktive.msaproduct.product.api.request.AddProductRequest;
import com.github.attacktive.msaproduct.product.api.request.UpdateProductRequest;
import com.github.attacktive.msaproduct.product.domain.Product;
import org.springframework.lang.Nullable;

public interface ProductUseCase {
	Product getProduct(long id);

	List<Product> getProductsByPagination(@Nullable Integer page, @Nullable Integer size);

	Product addProduct(AddProductRequest addProductRequest);

	Product updateProduct(long id, UpdateProductRequest updateProductRequest);

	void deleteProduct(long id);
}
