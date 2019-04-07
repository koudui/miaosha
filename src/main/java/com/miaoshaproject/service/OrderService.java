package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.OrderModel;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/6 23:05
 * @software: IntelliJ IDEA
 * @file: OrderService
 * @description:
 */
//用于处理订单交易
public interface OrderService {
    //1.通过前端url上传过来的id，然后在下单接口内判断对应id是否属于商品并且活动是否已经开始
    //2.直接在下单接口中判断对应的商品是否存在秒杀活动，若存在进行中的活动则按照秒杀价格下单
    //使用方法1,解决多终端秒杀下单

    OrderModel createOrder(Integer userId,Integer itemId,Integer promoId,Integer amount) throws BusinessException;
}
