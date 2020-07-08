package com.nnx.application;

import java.util.List;

import com.nnx.controller.dto.ProvisionProductRequest;
import com.nnx.controller.dto.ProvisionProductResponse;

public interface PriceEngineService {

    ProvisionProductResponse provisionProduct(ProvisionProductRequest provisionProductRequest);

    List<ProvisionProductResponse> listProducts();
}
