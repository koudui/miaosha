package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.ItemVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/5 23:14
 * @software: IntelliJ IDEA
 * @file: ItemController
 * @description:
 */
@Controller("/item")
@RequestMapping("/item")
@CrossOrigin(allowedHeaders = {"*"},allowCredentials = "true") //springboot前端请求的跨域问题
public class ItemController extends BaseController {
    @Autowired
    private ItemService itemService;
    //创建商品的controller
    @RequestMapping(value = "/create",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMAT})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name="title") String title,
                                       @RequestParam(name="description") String description,
                                       @RequestParam(name="price") BigDecimal price,
                                       @RequestParam(name="stock") Integer stock,
                                       @RequestParam(name="imgUrl") String imgUrl) throws BusinessException {
        ItemModel itemModel=new ItemModel();
        itemModel.setStock(stock);
        itemModel.setPrice(price);
        itemModel.setDescription(description);
        itemModel.setImgUrl(imgUrl);
        itemModel.setTitle(title);

        ItemModel itemModel1Return=itemService.createItem(itemModel);
        ItemVO itemVO=this.convertItemVOFromItemModel(itemModel1Return);
        return CommonReturnType.create(itemVO);
    }
    //浏览商品详情
    @RequestMapping(value = "/get",method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType get(@RequestParam(name="id") Integer id){
        ItemModel itemModel=itemService.getItemById(id);
        ItemVO itemVO=convertItemVOFromItemModel(itemModel);
        return CommonReturnType.create(itemVO);
    }
    private ItemVO convertItemVOFromItemModel(ItemModel itemModel){
        if(itemModel==null){
            return null;
        }
        ItemVO itemVO=new ItemVO();
        BeanUtils.copyProperties(itemModel,itemVO);
        return itemVO;
    }
}
