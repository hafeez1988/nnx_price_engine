package com.nnx.controller.dto;

/**
 * The calculate product cost response class.
 * 
 * @author hafeez
 */
public class CalculateProductCostResponse {

    private long productId;

    private String productName;

    private double calculatedTotalPrice;

    public long getProductId() {
        return productId;
    }

    public void setProductId(final long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public double getCalculatedTotalPrice() {
        return calculatedTotalPrice;
    }

    public void setCalculatedTotalPrice(final double calculatedTotalPrice) {
        this.calculatedTotalPrice = calculatedTotalPrice;
    }
}
