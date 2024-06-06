package com.github.attacktive.msaproduct.product.adapter.inbound;

import javax.validation.constraints.NotNull;

public record UpdateProductStockRequest(Long id, @NotNull Long stockChange) { }
