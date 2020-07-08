package com.nnx.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.nnx.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
