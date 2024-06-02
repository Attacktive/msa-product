package com.github.attacktive.msaproduct.product.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.lang.Nullable;

public record UpdateProductRequest(
	Long id,

	@NotBlank
	String name,

	@Nullable
	String description,

	@NotNull
	@Positive
	Long price
) implements ProductRequest {
	public UpdateProductRequest withId(Long id) {
		return new UpdateProductRequest(id, name, description, price);
	}
}
