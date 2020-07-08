package com.nnx.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.nnx.domain.Product;

/**
 * The product repository class that contains all the database transactions for product.
 * 
 * @author hafeez
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

}
