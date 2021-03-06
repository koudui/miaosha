package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.UserModel;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/1 22:53
 * @software: IntelliJ IDEA
 * @file: UserService
 * @description:
 */
public interface UserService {
    //通过用户id获取对象
    UserModel getUserById(Integer id);
    //根据前端的信息组成一个UserModel并作为参数完成用户的注册
    void register(UserModel userModel) throws BusinessException;
    /*
    * telphone 用户注册手机
    * encrptPassword用户加密后的密码
    * */
    //根据账号密码判断是否登录成功并返回一个UserModel类型
    UserModel validateLogin(String telphone,String encrptPassword) throws BusinessException;
}
