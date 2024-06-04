package com.github.attacktive.msaproduct.product.service;

import java.util.Set;

import com.github.attacktive.msaproduct.MsaProductApplication;
import com.github.attacktive.msaproduct.product.application.request.AddProductRequest;
import com.github.attacktive.msaproduct.product.application.request.UpdateProductRequest;
import com.github.attacktive.msaproduct.product.port.inbound.ProductUseCase;
import com.github.attacktive.msaproduct.product.adapter.outbound.persristence.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = MsaProductApplication.class)
@DisplayName("ProductUseCase")
@Transactional
class ProductUseCaseTest {
	@Autowired
	private ProductUseCase productUseCase;

	@Autowired
	private ProductRepository productRepository;

	@BeforeEach
	void truncateTable() {
		productRepository.deleteAll();
	}

	@Test
	@DisplayName("addProduct")
	void testAddProduct() {
		var productName = "product-name";
		var productDescription = "product-description";
		var productPrice = 10L;

		var addProductRequest = new AddProductRequest(productName, productDescription, productPrice);
		var product = productUseCase.addProduct(addProductRequest);

		Assertions.assertEquals(productName, product.name());
		Assertions.assertEquals(productDescription, product.description());
		Assertions.assertEquals(productPrice, product.price());
	}

	@Test
	@DisplayName("getProduct")
	void testGetProduct() {
		var productName = "product-name";
		var productDescription = "product-description";
		var productPrice = 10L;

		var addProductRequest = new AddProductRequest(productName, productDescription, productPrice);
		var added = productUseCase.addProduct(addProductRequest);

		var retrieved = productUseCase.getProduct(added.id());
		Assertions.assertEquals(productName, retrieved.name());
		Assertions.assertEquals(productDescription, retrieved.description());
		Assertions.assertEquals(productPrice, retrieved.price());
	}

	@Test
	@DisplayName("getProductsByPagination: with great page size")
	void testGetProductsByPaginationWithGreatSize() {
		productUseCase.addProduct(new AddProductRequest("p1", null, 1L));
		productUseCase.addProduct(new AddProductRequest("p2", null, 2L));
		productUseCase.addProduct(new AddProductRequest("p3", null, 3L));

		var products = productUseCase.getProductsByPagination(1, 10, null);
		Assertions.assertEquals(3, products.size());
	}

	@Test
	@DisplayName("getProductsByPagination: with smaller size")
	void testGetProductsByPaginationWithSmallerSize() {
		productUseCase.addProduct(new AddProductRequest("p1", null, 1L));
		productUseCase.addProduct(new AddProductRequest("p2", null, 2L));
		productUseCase.addProduct(new AddProductRequest("p3", null, 3L));

		var products = productUseCase.getProductsByPagination(1, 2, null);
		Assertions.assertEquals(2, products.size());
	}

	@Test
	@DisplayName("getProductsByPagination: the 2nd page")
	void testGetProductsByPaginationThe2ndPage() {
		productUseCase.addProduct(new AddProductRequest("p1", null, 1L));
		productUseCase.addProduct(new AddProductRequest("p2", null, 2L));
		productUseCase.addProduct(new AddProductRequest("p3", null, 3L));

		var products = productUseCase.getProductsByPagination(2, 2, null);
		Assertions.assertEquals(1, products.size());
	}

	@Test
	@DisplayName("getProductsByPagination: by Product IDs")
	void testGetProductsByPaginationByProductIds() {
		productUseCase.addProduct(new AddProductRequest("p1", null, 1L));
		var secondProduct = productUseCase.addProduct(new AddProductRequest("p2", null, 2L));

		var products = productUseCase.getProductsByPagination(1, 5150, Set.of(secondProduct.id()));
		Assertions.assertEquals(1, products.size());
		Assertions.assertEquals(secondProduct, products.get(0));
	}

	@Test
	@DisplayName("updateProduct")
	void testUpdateProduct() {
		var addProductRequest = new AddProductRequest("product-name", "product-description", 100L);
		var added = productUseCase.addProduct(addProductRequest);

		var productCount = productRepository.count();
		var newName = "new-product-name";
		var newPrice = 200L;
		var updateProductRequest = new UpdateProductRequest(added.id(), newName, null, newPrice);
		var updated = productUseCase.updateProduct(added.id(), updateProductRequest);

		Assertions.assertEquals(newName, updated.name());
		Assertions.assertNull(updated.description());
		Assertions.assertEquals(newPrice, updated.price());
		Assertions.assertEquals(productCount, productRepository.count());
	}

	@Test
	@DisplayName("deleteProduct")
	void testDeleteProduct() {
		var addProductRequest = new AddProductRequest("product-name", "product-description", 100L);
		var added = productUseCase.addProduct(addProductRequest);
		productUseCase.deleteProduct(added.id());

		var products = productUseCase.getProductsByPagination(1, 10, null);
		Assertions.assertEquals(0, products.size());
	}
}
