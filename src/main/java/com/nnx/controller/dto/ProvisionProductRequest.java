package com.nnx.controller.dto;

/**
 * The provision product request class.
 * 
 * @author hafeez
 */
public class ProvisionProductRequest {

    private long productId;

    private String name;

    private String category;

    private double pricePerCarton;

    private long unitsPerCarton;

    public long getProductId() {
        return productId;
    }

    public void setProductId(final long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public double getPricePerCarton() {
        return pricePerCarton;
    }

    public void setPricePerCarton(final double pricePerCarton) {
        this.pricePerCarton = pricePerCarton;
    }

    public long getUnitsPerCarton() {
        return unitsPerCarton;
    }

    public void setUnitsPerCarton(final long unitsPerCarton) {
        this.unitsPerCarton = unitsPerCarton;
    }
}
