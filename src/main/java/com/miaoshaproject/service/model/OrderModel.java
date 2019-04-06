package com.miaoshaproject.service.model;

import java.math.BigDecimal;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/6 22:53
 * @software: IntelliJ IDEA
 * @file: OrderModel
 * @description:
 */
//用户交易模型
public class OrderModel {
    //商品订单号为有一定规律的字符串
    private String id;
    //买家id
    private Integer userId;
    //商品id
    private Integer itemId;
    //购买商品的单价
    private BigDecimal itemPrice;
    //购买商品的数量
    private Integer amount;
    //购买商品的总金额
    private BigDecimal orderPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}
