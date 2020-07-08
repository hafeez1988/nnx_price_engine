package com.nnx.controller.dto;

/**
 * The calculate product cost request class.
 * 
 * @author hafeez
 */
public class CalculateProductCostRequest {

    private long productId;

    private long numberOfUnits;

    public long getProductId() {
        return productId;
    }

    public void setProductId(final long productId) {
        this.productId = productId;
    }

    public long getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(final long numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }
}
