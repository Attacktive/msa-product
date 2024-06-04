package com.github.attacktive.msaproduct.product.application.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public record ErrorResponse(@JsonIgnore HttpStatus httpStatus, String message) {
	@JsonGetter("httpStatus")
	public int httpStatusValue() {
		return httpStatus.value();
	}
}
