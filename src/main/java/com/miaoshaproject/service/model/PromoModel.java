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

    //秒杀活动的状态，1表示还没开始，2表示正在进行中，3表示已经结束
    //将活动状态放在model中的好处是外层service不用多次判断活动状态，而是可以直接
    //从model中获取。
    private Integer status;

    //促销活动名称
    private String promoName;

    //促销活动开始时间
    private DateTime startDate;

    //促销活动结束时间
    private DateTime endDate;

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

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
