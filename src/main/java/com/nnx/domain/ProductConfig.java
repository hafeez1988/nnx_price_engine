package com.nnx.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "config_id")
    private long configId;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "price_per_unit")
    private double pricePerUnit;

    @Column(name = "price_per_carton")
    private double pricePerCarton;

    @Column(name = "units_per_carton")
    private long unitsPerCarton;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;

    public long getConfigId() {
        return configId;
    }

    public void setConfigId(long configId) {
        this.configId = configId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public double getPricePerCarton() {
        return pricePerCarton;
    }

    public void setPricePerCarton(double pricePerCarton) {
        this.pricePerCarton = pricePerCarton;
    }

    public long getUnitsPerCarton() {
        return unitsPerCarton;
    }

    public void setUnitsPerCarton(long unitsPerCarton) {
        this.unitsPerCarton = unitsPerCarton;
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
}
