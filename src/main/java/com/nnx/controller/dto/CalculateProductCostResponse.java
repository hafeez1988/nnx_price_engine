package com.nnx.controller.dto;

public class CalculateProductCostResponse {

    private long productId;

    private String productName;

    private double calculatedTotalPrice;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getCalculatedTotalPrice() {
        return calculatedTotalPrice;
    }

    public void setCalculatedTotalPrice(double calculatedTotalPrice) {
        this.calculatedTotalPrice = calculatedTotalPrice;
    }
}
