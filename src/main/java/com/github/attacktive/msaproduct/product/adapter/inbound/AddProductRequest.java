package com.github.attacktive.msaproduct.product.adapter.inbound;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.lang.Nullable;

public record AddProductRequest(
	@NotBlank
	String name,

	@Nullable
	String description,

	@NotNull
	@Positive
	Long price,

	@NotNull
	@PositiveOrZero
	Long stock
) implements ProductRequest {
	@Override
	public Long id() {
		return null;
	}
}
