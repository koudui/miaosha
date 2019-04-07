package com.miaoshaproject.service.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/7 15:31
 * @software: IntelliJ IDEA
 * @file: PromoModel
 * @description:
 */
public class PromoModel {
    private Integer id;

    //促销活动名称
    private String promoName;

    //促销活动开始时间
    private DateTime startDate;

    //促销活动的商品
    private Integer itemId;

    //促销商品的价格
    private BigDecimal promoPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(BigDecimal promoPrice) {
        this.promoPrice = promoPrice;
    }
}
