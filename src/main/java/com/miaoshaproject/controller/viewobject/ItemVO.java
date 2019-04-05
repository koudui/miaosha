package com.miaoshaproject.controller.viewobject;

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
}
