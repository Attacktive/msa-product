package com.github.attacktive.msaproduct.product.application.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.lang.Nullable;

public record AddProductRequest(
	@NotBlank
	String name,

	@Nullable
	String description,

	@NotNull
	@Positive
	Long price
) implements ProductRequest {
	@Override
	public Long id() {
		return null;
	}
}
