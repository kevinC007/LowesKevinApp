package com.example.shuangzhecheng.loweskevinapp.model;

/**
 * Created by shuangzhecheng on 11/29/17.
 */

public class CartItem {
    private Products.ProductBean productBean;
    private String quantity;

    public CartItem(Products.ProductBean productBean, String quantity) {
        this.productBean = productBean;
        this.quantity = quantity;
    }

    public Products.ProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(Products.ProductBean productBean) {
        this.productBean = productBean;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
