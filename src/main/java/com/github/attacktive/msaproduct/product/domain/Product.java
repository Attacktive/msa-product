package com.github.attacktive.msaproduct.product.domain;

import lombok.Builder;

@Builder
public record Product(long id, String name, String description, long price, long stock) { }
