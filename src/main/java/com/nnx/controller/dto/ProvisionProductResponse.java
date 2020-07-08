package com.nnx.controller.dto;

import java.sql.Timestamp;

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

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public ProductConfigDto getProductConfig() {
        return productConfig;
    }

    public void setProductConfig(ProductConfigDto productConfig) {
        this.productConfig = productConfig;
    }
}
