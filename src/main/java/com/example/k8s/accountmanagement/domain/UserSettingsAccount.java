package com.example.k8s.accountmanagement.domain;

import java.io.Serializable;

public class UserSettingsAccount implements Serializable {
    private String name;
    private Integer order;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
