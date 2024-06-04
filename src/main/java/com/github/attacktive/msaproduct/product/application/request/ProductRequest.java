package com.github.attacktive.msaproduct.product.application.request;

import org.springframework.lang.Nullable;

public interface ProductRequest {
	@Nullable
	Long id();

	String name();

	String description();

	Long price();
}
