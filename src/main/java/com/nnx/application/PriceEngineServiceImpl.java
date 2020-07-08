package com.nnx.application;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.nnx.common.ProductCostCalculatorUtil;
import com.nnx.controller.dto.CalculateProductCostRequest;
import com.nnx.controller.dto.CalculateProductCostResponse;
import com.nnx.controller.dto.ProductConfigDto;
import com.nnx.controller.dto.ProvisionProductRequest;
import com.nnx.controller.dto.ProvisionProductResponse;
import com.nnx.domain.Product;
import com.nnx.domain.ProductConfig;
import com.nnx.infrastructure.ProductConfigRepository;
import com.nnx.infrastructure.ProductRepository;

@Service
public class PriceEngineServiceImpl implements PriceEngineService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConfigRepository productConfigRepository;

    @Override
    public ProvisionProductResponse provisionProduct(ProvisionProductRequest provisionProductRequest) {

        Assert.notNull(provisionProductRequest, "Provision product request is null");

        Product product = null;
        ProductConfig productConfig = null;

        if (provisionProductRequest.getProductId() > 0) {
            Optional<Product> productRecord = productRepository.findById(provisionProductRequest.getProductId());
            if (!productRecord.isEmpty() && productRecord.get() != null) {
                product = productRecord.get();
                productConfig = productConfigRepository.findByProductId(product.getProductId());
            }
        }

        /*
         * Extracting product data
         */
        if (product == null) {
            Assert.hasText(provisionProductRequest.getName(), "Product name is empty");
            Assert.hasText(provisionProductRequest.getCategory(), "Product category is empty");

            product = new Product();
            product.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        }

        product.setName(StringUtils.hasText(provisionProductRequest.getName()) ? provisionProductRequest.getName()
                : product.getName());
        product.setCategory(StringUtils.hasText(provisionProductRequest.getCategory())
                ? provisionProductRequest.getCategory() : product.getCategory());
        product.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        product = productRepository.save(product);

        /*
         * Extracting product config data
         */
        if (productConfig == null) {
            Assert.isTrue(provisionProductRequest.getPricePerUnit() > 0, "Price per unit is unavailable");
            Assert.isTrue(provisionProductRequest.getPricePerCarton() > 0, "Price per carton is unavailable");
            Assert.isTrue(provisionProductRequest.getUnitsPerCarton() > 0, "Units per carton is unavailable");

            productConfig = new ProductConfig();
            productConfig.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            productConfig.setProductId(product.getProductId());
        }

        productConfig.setPricePerUnit((provisionProductRequest.getPricePerUnit() > 0)
                ? provisionProductRequest.getPricePerUnit() : productConfig.getPricePerUnit());
        productConfig.setPricePerCarton((provisionProductRequest.getPricePerCarton() > 0)
                ? provisionProductRequest.getPricePerCarton() : productConfig.getPricePerCarton());
        productConfig.setUnitsPerCarton((provisionProductRequest.getUnitsPerCarton() > 0)
                ? provisionProductRequest.getUnitsPerCarton() : productConfig.getUnitsPerCarton());
        productConfig.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        productConfig = productConfigRepository.save(productConfig);

        return transformToProvisionProductResponse(product, productConfig);
    }

    @Override
    public List<ProvisionProductResponse> listProducts() {

        List<ProvisionProductResponse> productResponseList = new ArrayList<ProvisionProductResponse>();

        Iterable<Product> products = productRepository.findAll();

        for (Product product : products) {
            ProductConfig productConfig = productConfigRepository.findByProductId(product.getProductId());
            ProvisionProductResponse productResponse = transformToProvisionProductResponse(product, productConfig);
            productResponseList.add(productResponse);
        }

        return productResponseList;
    }

    @Override
    public CalculateProductCostResponse calculateCost(CalculateProductCostRequest calculateProductCostRequest) {

        Assert.notNull(calculateProductCostRequest, "Calculate product cost request is null");
        Assert.isTrue(calculateProductCostRequest.getProductId() > 0, "Product id is not provided");

        long numberOfProductUnits = calculateProductCostRequest.getNumberOfUnits();
        Assert.isTrue(numberOfProductUnits > 0, "Number product units is not provided");

        Optional<Product> productRecord = productRepository.findById(calculateProductCostRequest.getProductId());
        if (productRecord.isEmpty() || productRecord.get() == null) {
            throw new IllegalArgumentException("Product is unavailable");
        }

        Product product = productRecord.get();
        ProductConfig productConfig = productConfigRepository.findByProductId(product.getProductId());

        double calculatedTotalPrice = ProductCostCalculatorUtil.getCalculatedCost(numberOfProductUnits, productConfig);

        return transformToCalculateProductCostResponse(calculatedTotalPrice, product);
    }

    private ProvisionProductResponse transformToProvisionProductResponse(Product product, ProductConfig productConfig) {

        ProductConfigDto productConfigDto = new ProductConfigDto();
        productConfigDto.setConfigId(productConfig.getConfigId());
        productConfigDto.setCreatedTime(productConfig.getCreatedTime());
        productConfigDto.setPricePerCarton(productConfig.getPricePerCarton());
        productConfigDto.setPricePerUnit(productConfig.getPricePerUnit());
        productConfigDto.setUnitsPerCarton(productConfig.getUnitsPerCarton());
        productConfigDto.setProductId(productConfig.getProductId());
        productConfigDto.setUpdatedTime(productConfig.getUpdatedTime());

        ProvisionProductResponse provisionProductResponse = new ProvisionProductResponse();
        provisionProductResponse.setCategory(product.getCategory());
        provisionProductResponse.setCreatedTime(product.getCreatedTime());
        provisionProductResponse.setDeleted(product.isDeleted());
        provisionProductResponse.setName(product.getName());
        provisionProductResponse.setProductConfig(productConfigDto);
        provisionProductResponse.setProductId(product.getProductId());
        provisionProductResponse.setUpdatedTime(product.getUpdatedTime());

        return provisionProductResponse;
    }

    private CalculateProductCostResponse transformToCalculateProductCostResponse(double calculatedTotalPrice,
            Product product) {

        CalculateProductCostResponse calculateProductCostResponse = new CalculateProductCostResponse();
        calculateProductCostResponse.setCalculatedTotalPrice(calculatedTotalPrice);
        calculateProductCostResponse.setProductName(product.getName());
        calculateProductCostResponse.setProductId(product.getProductId());

        return calculateProductCostResponse;
    }
}
