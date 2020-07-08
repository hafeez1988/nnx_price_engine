package com.nnx.application;

import java.util.List;

import com.nnx.controller.dto.CalculateProductCostRequest;
import com.nnx.controller.dto.CalculateProductCostResponse;
import com.nnx.controller.dto.ProvisionProductRequest;
import com.nnx.controller.dto.ProvisionProductResponse;

/**
 * The price engine service interface.
 * 
 * @author hafeez
 */
public interface PriceEngineService {

    ProvisionProductResponse provisionProduct(ProvisionProductRequest provisionProductRequest);

    List<ProvisionProductResponse> listProducts();

    CalculateProductCostResponse calculateCost(CalculateProductCostRequest calculateProductCostRequest);
}
