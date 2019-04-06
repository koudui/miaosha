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
    OrderModel createOrder(Integer userId,Integer itemId,Integer amount) throws BusinessException;
}
