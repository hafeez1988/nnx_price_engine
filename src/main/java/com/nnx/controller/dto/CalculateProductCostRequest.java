package com.nnx.controller.dto;

public class CalculateProductCostRequest {

    private long productId;

    private long numberOfUnits;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(long numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }
}
