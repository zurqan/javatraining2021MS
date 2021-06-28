package com.training.servicea.adapter.rest.dto;


public class PurchaseOrder {

    private String id;

    private String createdBy;

    private String location;

    private int totalPrice;

    public PurchaseOrder(String id, String createdBy, String location, int totalPrice) {
        this.id = id;
        this.createdBy = createdBy;
        this.location = location;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id='" + id + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", location='" + location + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
