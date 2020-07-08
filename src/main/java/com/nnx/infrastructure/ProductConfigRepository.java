package com.nnx.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.nnx.domain.ProductConfig;

/**
 * The product configuration repository class that contains all the database transactions for product configuration.
 * 
 * @author hafeez
 */
public interface ProductConfigRepository extends CrudRepository<ProductConfig, Long> {

    ProductConfig findByProductId(long productId);
}
