package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.ItemModel;

import java.util.List;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/5 22:31
 * @software: IntelliJ IDEA
 * @file: ItemService
 * @description:
 */
public interface ItemService {
    //创建商品
    ItemModel createItem(ItemModel itemModel) throws BusinessException;
    //浏览商品列表
    List<ItemModel> listItem();
    //查看商品详情
    ItemModel getItemById(Integer id);
    //落单减库存
    boolean decreaseStock(Integer itemId,Integer amount) throws BusinessException;
    //销量增加
    void increaseSales(Integer itemId,Integer amount) throws BusinessException;
}
