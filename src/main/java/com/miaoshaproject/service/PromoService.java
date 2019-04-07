package com.miaoshaproject.service;

import com.miaoshaproject.service.model.PromoModel;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/7 15:45
 * @software: IntelliJ IDEA
 * @file: PromoService
 * @description:
 */
public interface PromoService {
    //根据商品id获取即将进行的或正在进行的促销活动
    PromoModel getPromoByItemId(Integer itemId);
}
