package com.miaoshaproject.controller.viewobject;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/5 23:14
 * @software: IntelliJ IDEA
 * @file: ItemVO
 * @description:
 */
public class ItemVO {
    private Integer id;

    //商品名称
    private String title;

    //商品库存
    private Integer stock;

    //商品价格
    private BigDecimal price;

    //商品描述
    private String description;

    //商品图片
    private String imgUrl;

    //商品销量
    private Integer sales;

    //记录商品是否处于秒杀活动状态，0表示没有秒杀活动，1表示秒杀活动待开始，2表示秒杀活动正在进行中
    private Integer promoStatus;

    //秒杀活动的商品价格
    private BigDecimal promoPrice;

    //秒杀活动的id，用于商品交易
    private Integer promoId;

    //秒杀活动的开始时间，用于做即将开始秒杀活动的倒计时
    private DateTime startDate;

    public Integer getPromoStatus() {
        return promoStatus;
    }

    public void setPromoStatus(Integer promoStatus) {
        this.promoStatus = promoStatus;
    }

    public BigDecimal getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(BigDecimal promoPrice) {
        this.promoPrice = promoPrice;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }
}
