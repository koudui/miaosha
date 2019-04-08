package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.OrderDOMapper;
import com.miaoshaproject.dao.SequenceDOMapper;
import com.miaoshaproject.dataobject.OrderDO;
import com.miaoshaproject.dataobject.SequenceDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.service.model.OrderModel;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/6 23:07
 * @software: IntelliJ IDEA
 * @file: OrderServiceImpl
 * @description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private SequenceDOMapper sequenceDOMapper;//用于生成一个唯一6位自增数

    @Autowired
    private ItemService itemService;//需要商品相关信息

    @Autowired
    private UserService userService;//需要用户相关信息

    @Autowired
    private OrderDOMapper orderDOMapper;//订单处理相关

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId,Integer promoId, Integer amount) throws BusinessException {
        //1.校验下单状态，用户是否合法，商品是否存在，购买数量是否正确
        ItemModel itemModel=itemService.getItemById(itemId);
        if(itemModel==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"商品信息不存在");
        }
        UserModel userModel=userService.getUserById(userId);
        if(userModel==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户信息不存在");
        }
        //不允许不购买或者单次购买超过10个的交易行为
        if(amount<=0||amount>10){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"购买数量不合法");
        }

        //校验活动信息
        if(promoId!=null){
            //校验对应活动是否存在这个适用商品
            if(promoId.intValue()!=itemModel.getPromoModel().getId()){
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"秒杀活动信息不正确");
            }else if(itemModel.getPromoModel().getStatus().intValue()!=2){
                //校验活动是否在进行中
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"秒杀活动还未开始");
            }
        }

        //2.落单减库存
        boolean result=itemService.decreaseStock(itemId,amount);
        if(!result){
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }

        //3.订单入库
        OrderModel orderModel=new OrderModel();
        orderModel.setAmount(amount);
        orderModel.setItemId(itemId);
        orderModel.setUserId(userId);
        //因为有促销活动，因此商品价格分情况设置
        if(promoId!=null){
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoPrice());
        }else{
            orderModel.setItemPrice(itemModel.getPrice());
        }
        orderModel.setPromoId(promoId);
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(amount)));

        //生成交易订单号
        orderModel.setId(generatorOrderNO());

        OrderDO orderDO=convertOrderDoFromOrderModel(orderModel);
        orderDOMapper.insertSelective(orderDO);

        //增加销量
        itemService.increaseSales(itemId,amount);
        //4.返回前端

        return orderModel;
    }
    /*
    由于调用generatorOrderNO方法的已经是一个事务，因此generatorOrderNO也会挂在事务中，
    但是如果generatorOrderNO执行失败导致回滚的话，下一次还是会使用到sequence的值，
    这并不是所期望的全局唯一性，因此需要在generatorOrderNO方法上也添加一个事务
    的注解，并且传入一个REQUIRES_NEW参数，表示不管是否在事务中，都会创建一个新的事务，
    并且在代码执行完后提交事务    */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generatorOrderNO(){
        //订单号有16位
        StringBuilder stringBuilder=new StringBuilder();//用StringBuilder做字符串的拼接

        //前8位为日期，年月日
        LocalDateTime dateTime=LocalDateTime.now();
        String nowDate=dateTime.format(DateTimeFormatter.ISO_DATE).replace("-","");
        stringBuilder.append(nowDate);

        //中间6位为自增的数字，保证订单的唯一性
        //获取当前sequence
        int sequence=0;
        //如果数据库中sequence_info的字段修改了，此处的order_info也需要修改
        SequenceDO sequenceDO=sequenceDOMapper.getSequenceByName("order_info");
        sequence=sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue()+sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        String sequnceStr=String.valueOf(sequence);
        //假设自增数字不会超过6位数，即在同一时刻允许的最大提交订单量不超过6位数
         for(int i=0;i<6-sequnceStr.length();i++){
            stringBuilder.append("0");
        }
        stringBuilder.append(sequnceStr);

        //最后两位为分库分表位，此处暂时写死
        stringBuilder.append("00");

        return stringBuilder.toString();
    }
   private OrderDO convertOrderDoFromOrderModel(OrderModel orderModel){
        if (orderModel==null){
            return null;
        }
        OrderDO orderDO=new OrderDO();
        BeanUtils.copyProperties(orderModel,orderDO);
        //类型不同的应当转换后设置
        orderDO.setItemPrice(orderModel.getItemPrice().doubleValue());
        orderDO.setOrderPrice(orderModel.getOrderPrice().doubleValue());
        return orderDO;
    }
}
