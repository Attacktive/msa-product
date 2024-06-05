package com.github.attacktive.msaproduct.product.port.inbound;

import java.util.List;
import java.util.Set;

import com.github.attacktive.msaproduct.product.adapter.inbound.AddProductRequest;
import com.github.attacktive.msaproduct.product.adapter.inbound.UpdateProductRequest;
import com.github.attacktive.msaproduct.product.domain.Product;
import org.springframework.lang.Nullable;

public interface ProductUseCase {
	Product getProduct(long id);

	List<Product> getProductsByPagination(@Nullable Integer page, @Nullable Integer size, @Nullable Set<Long> productIds);

	Product addProduct(AddProductRequest addProductRequest);

	Product updateProduct(long id, UpdateProductRequest updateProductRequest);

	void deleteProduct(long id);
}
