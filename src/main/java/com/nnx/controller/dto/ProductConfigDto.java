package com.nnx.controller.dto;

import java.sql.Timestamp;

/**
 * The product configuration dto class.
 * 
 * @author hafeez
 */
public class ProductConfigDto {

    private long configId;

    private long productId;

    private double pricePerUnit;

    private double pricePerCarton;

    private long unitsPerCarton;

    private Timestamp createdTime;

    private Timestamp updatedTime;

    public long getConfigId() {
        return configId;
    }

    public void setConfigId(final long configId) {
        this.configId = configId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(final long productId) {
        this.productId = productId;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(final double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
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

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(final Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(final Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }
}
