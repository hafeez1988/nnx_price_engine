package com.nnx.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.nnx.domain.ProductConfig;

public interface ProductConfigRepository extends CrudRepository<ProductConfig, Long> {

    ProductConfig findByProductId(long productId);
}
