package com.miaoshaproject.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/5 22:11
 * @software: IntelliJ IDEA
 * @file: ItemModel
 * @description:
 */
public class ItemModel {
    private Integer id;

    //商品名称
    @NotBlank(message = "商品名不能为空")
    private String title;

    //商品库存
    @NotNull(message = "商品库存不能为空")
    private Integer stock;

    //商品价格
    @NotNull(message = "商品价格不能为空")
    @Min(value = 0,message = "商品价格必须大于0")
    private BigDecimal price;

    //商品描述
    @NotBlank(message = "商品描述不能为空")
    private String description;

    //商品图片
    @NotBlank(message = "商品图片信息不能为空")
    private String imgUrl;

    //商品销量
    private Integer sales;

    //使用聚合模型，如果PromoModel不为空，表示还有未结束的促销活动，包括
    //未开始的和正在进行中的
    private PromoModel promoModel;

    public PromoModel getPromoModel() {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel) {
        this.promoModel = promoModel;
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
