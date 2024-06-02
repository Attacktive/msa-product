package com.github.attacktive.msaproduct.product.api;

import java.util.List;
import java.util.Set;
import javax.validation.Valid;

import com.github.attacktive.msaproduct.product.api.request.AddProductRequest;
import com.github.attacktive.msaproduct.product.api.request.UpdateProductRequest;
import com.github.attacktive.msaproduct.product.domain.Product;
import com.github.attacktive.msaproduct.product.service.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductUseCase productUseCase;

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public Product getProduct(@PathVariable long id) {
		return productUseCase.getProduct(id);
	}

	@GetMapping
	public ResponseEntity<List<Product>> getProductsByPagination(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size, @RequestParam(required = false, name = "product-id") Set<Long> productIds) {
		ResponseEntity<List<Product>> responseEntity;
		var products = productUseCase.getProductsByPagination(page, size, productIds);
		if (products.isEmpty()) {
			responseEntity = new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
		} else {
			responseEntity = ResponseEntity.ok(products);
		}

		return responseEntity;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	// fixme: the @Valid annotation is not working
	public Product addProduct(@RequestBody @Valid AddProductRequest addProductRequest) {
		return productUseCase.addProduct(addProductRequest);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	// fixme: the @Valid annotation is not working
	public Product updateProduct(@PathVariable long id, @RequestBody @Valid UpdateProductRequest updateProductRequest) {
		return productUseCase.updateProduct(id, updateProductRequest);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable long id) {
		productUseCase.deleteProduct(id);
	}
}
