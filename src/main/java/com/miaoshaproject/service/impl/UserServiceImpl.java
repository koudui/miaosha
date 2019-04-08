package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dao.UserPasswordDOMapper;
import com.miaoshaproject.dataobject.UserDO;
import com.miaoshaproject.dataobject.UserPasswordDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/1 22:55
 * @software: IntelliJ IDEA
 * @file: UserServiceImpl
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDOMapper userDOMapper;//自动装配一个UserDOMapper，并可调用其中的方法操作数据库

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;//用户的账号和密码分开存储

    @Autowired
    private ValidatorImpl validator;//引入校验规则

    @Override
    public UserModel getUserById(Integer id) {
        //通过userdomapper获取到对应用户的dataobject
        UserDO userDO=userDOMapper.selectByPrimaryKey(id);
        if(userDO==null){
            return null;
        }
        //通过用户的id获取对应的加密密码
        UserPasswordDO userPasswordDO=userPasswordDOMapper.selectByUserId(userDO.getId());
        return convertFromDataObject(userDO,userPasswordDO);
    }

    @Override
    public void register(UserModel userModel) throws BusinessException {
        //判断对象是否为空
        if(userModel==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //判断参数是否存在为空的情况
//        if(StringUtils.isEmpty(userModel.getName())
//                || userModel.getGender()==null
//                || userModel.getAge()==null
//                || StringUtils.isEmpty(userModel.getTelphone())){
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//        }
        ValidationResult result=validator.validate(userModel);
        if(result.getHasErr()==true){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }

        //将model-->转为dataobject
        UserDO userDO=convertFromModel(userModel);
        try{
            userDOMapper.insertSelective(userDO);
        }catch (DuplicateKeyException e){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"手机号已经注册");
        }
//        userDOMapper.insertSelective(userDO);

        userModel.setId(userDO.getId());

        UserPasswordDO userPasswordDO=convertPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);

        return;
    }

    @Override
    public UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException {
        //通过用户的telphone获取用户信息
        UserDO userDO=userDOMapper.selectByTelphone(telphone);
        if(userDO==null){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO=userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel=convertFromDataObject(userDO,userPasswordDO);

        //对比用户信息内的加密密码是否和传输进来的密码相同
        if(!StringUtils.equals(encrptPassword,userModel.getEncrptPassword())){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return userModel;

    }

    private UserPasswordDO convertPasswordFromModel(UserModel userModel){
        if(userModel==null){
            return null;
        }
        UserPasswordDO userPasswordDO=new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }
    private UserDO convertFromModel(UserModel userModel){
        if(userModel==null){
            return null;
        }
        UserDO userDO=new UserDO();
        //相同的属性会复制，不同的会跳过
        BeanUtils.copyProperties(userModel,userDO);
        return userDO;
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){
        if(userDO==null){
            return null;
        }
        UserModel userModel=new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        if(userPasswordDO!=null) {
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }
        return userModel;
    }
}
