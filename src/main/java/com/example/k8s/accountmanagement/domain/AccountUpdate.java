package com.example.k8s.accountmanagement.domain;

public class AccountUpdate {
    private Integer newOrder;
    private String newName;

    public Integer getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(Integer newOrder) {
        this.newOrder = newOrder;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
