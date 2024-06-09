package com.github.attacktive.msaproduct.product.adapter.inbound;

import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.github.attacktive.msaproduct.product.adapter.NegativeStockException;
import com.github.attacktive.msaproduct.product.adapter.NoSuchProductException;
import com.github.attacktive.msaproduct.product.adapter.ProductInUseException;
import com.github.attacktive.msaproduct.product.domain.Product;
import com.github.attacktive.msaproduct.product.port.inbound.ProductUseCase;
import com.github.attacktive.msaproduct.product.port.outbound.ProductPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements ProductUseCase {
	private static final Lock lock = new ReentrantLock();

	private final ProductPort productPort;
	private final WebClient webClient;

	@Override
	public Product getProduct(long id) {
		return productPort.findById(id)
			.orElseThrow(() -> new NoSuchProductException(id));
	}

	@Override
	public List<Product> getProductsByPagination(@Nullable Integer page, @Nullable Integer size, Set<Long> productIds) {
		return productPort.findAll(page, size, productIds);
	}

	@Override
	public Product addProduct(AddProductRequest addProductRequest) {
		return productPort.save(addProductRequest);
	}

	@Override
	public Product updateProduct(long id, UpdateProductRequest updateProductRequest) {
		var productId = productPort.findById(id)
			.map(Product::id)
			.orElseThrow(() -> new NoSuchProductException(id));

		return productPort.save(updateProductRequest.withId(productId));
	}

	@Override
	public Product updateProductStock(UpdateProductStockRequest updateProductStockRequest) {
		lock.lock();

		try {
			var updateProductRequest = productPort.findById(updateProductStockRequest.id())
				.map(found -> found.withStockChange(updateProductStockRequest.stockChange()))
				.map(UpdateProductRequest::new)
				.orElseThrow(() -> new NoSuchProductException(updateProductStockRequest.id()));

			var targetStock = updateProductRequest.stock();
			if (targetStock < 0) {
				throw new NegativeStockException();
			}

			return productPort.save(updateProductRequest);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void deleteProduct(long id) {
		var productId = productPort.findById(id)
			.map(Product::id)
			.orElseThrow(() -> new NoSuchProductException(id));

		ensureProductIsNotInUse(productId);

		productPort.deleteById(productId);
	}

	/**
	 * @throws ProductInUseException when the specified product is in use
	 */
	private void ensureProductIsNotInUse(long productId) throws ProductInUseException {
		webClient.head()
			.uri(uriBuilder -> uriBuilder
				.path("/products/")
				.path(String.valueOf(productId))
				.build()
			)
			.retrieve()
			.onStatus(HttpStatus.OK::equals, clientResponse -> Mono.just(new ProductInUseException(productId)))
			.onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
			.toBodilessEntity()
			.log()
			.block();
	}
}
