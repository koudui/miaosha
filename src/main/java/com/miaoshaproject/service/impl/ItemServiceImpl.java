package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.ItemDOMapper;
import com.miaoshaproject.dao.ItemStockDOMapper;
import com.miaoshaproject.dataobject.ItemDO;
import com.miaoshaproject.dataobject.ItemStockDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.PromoService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.service.model.PromoModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/5 22:33
 * @software: IntelliJ IDEA
 * @file: ItemServiceImpl
 * @description:
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ValidatorImpl validator;//引入校验

    @Autowired
    private ItemDOMapper itemDOMapper;//关于商品的操作，商品的库存在另一张表上

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;//对商品库存的减操作相关

    @Autowired
    private PromoService promoService;//引入促销服务

    private ItemDO convertItemDOFromItemModel(ItemModel itemModel){
        if(itemModel==null){
            return null;
        }
        ItemDO itemDO=new ItemDO();
        BeanUtils.copyProperties(itemModel,itemDO);
        //copyProperties不会copy类型不一致的属性，需要类型转换，因为model中的price是BigDecimal类型

        itemDO.setPrice(itemModel.getPrice().doubleValue());
        return itemDO;
    }
    private ItemStockDO convertItemStackDOFromItemModel(ItemModel itemModel){
        if(itemModel==null){
            return null;
        }
        ItemStockDO itemStockDO=new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());
        itemStockDO.setStock(itemModel.getStock());
        return itemStockDO;
    }

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        //校验入参
        ValidationResult result=validator.validate(itemModel);
        if(result.getHasErr()==true){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }

        //转化itemmodel-->dataobject
        ItemDO itemDO=this.convertItemDOFromItemModel(itemModel);

        //写入数据库
        itemDOMapper.insertSelective(itemDO);
        itemModel.setId(itemDO.getId());

        ItemStockDO itemStockDO=this.convertItemStackDOFromItemModel(itemModel);
        itemStockDOMapper.insertSelective(itemStockDO);

        // 返回创建完成的对象
        return this.getItemById(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() {
        //在item_info数据表中查找按销量降序的所有商品
        List<ItemDO> itemDOList=itemDOMapper.listItem();
        //使用stream api和lambda表达式完成商品列表，即整合item_info和stock_info中的数据

        List<ItemModel> itemModelList=itemDOList.stream().map(itemDo->{
            ItemStockDO itemStockDO=itemStockDOMapper.selectByItemId(itemDo.getId());
            ItemModel itemModel=this.convertModelFromItemDOAndItemStockDO(itemDo,itemStockDO);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO=itemDOMapper.selectByPrimaryKey(id);
        if(itemDO==null){
            return null;
        }
        //通过item_id获取到对应的库存
        ItemStockDO itemStockDO=itemStockDOMapper.selectByItemId(itemDO.getId());

        //dataobject-->model
        ItemModel itemModel=convertModelFromItemDOAndItemStockDO(itemDO,itemStockDO);

        //获取活动商品的信息，通过聚合的方法
        PromoModel promoModel=promoService.getPromoByItemId(itemModel.getId());
        if(promoModel!=null && promoModel.getStatus().intValue()!=3){
            itemModel.setPromoModel(promoModel);
        }
        return itemModel;
    }

    @Override
    //对数据库进行更新操作应当是一种事务
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException {
        int affectRows=itemStockDOMapper.decreaseStock(itemId,amount);
        if(affectRows>0){
            //更新数据库成功
            return true;
        }else {
            //更新数据库失败
            return false;
        }
    }

    @Override
    @Transactional
    public void increaseSales(Integer itemId, Integer amount) throws BusinessException {
        itemDOMapper.increaseSales(itemId,amount);//这在SQL中的实现是通过累加的方式

    }


    //将item和item_stock转换成一个model并返回
    private ItemModel convertModelFromItemDOAndItemStockDO(ItemDO itemDO,ItemStockDO itemStockDO){
        ItemModel itemModel=new ItemModel();
        BeanUtils.copyProperties(itemDO,itemModel);
        itemModel.setPrice(new BigDecimal(itemDO.getPrice()));//注意类型要保持一致
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;
    }


}
