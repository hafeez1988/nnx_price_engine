package com.nnx.controller.dto;

import java.sql.Timestamp;

/**
 * The provision product response class.
 * 
 * @author hafeez
 */
public class ProvisionProductResponse {

    private long productId;

    private String name;

    private String category;

    private boolean deleted;

    private Timestamp createdTime;

    private Timestamp updatedTime;

    private ProductConfigDto productConfig;

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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
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

    public ProductConfigDto getProductConfig() {
        return productConfig;
    }

    public void setProductConfig(final ProductConfigDto productConfig) {
        this.productConfig = productConfig;
    }
}
