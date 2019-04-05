package com.miaoshaproject.error;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/3 19:51
 * @software: IntelliJ IDEA
 * @file: BusinessException
 * @description:
 */
//包装器业务异常类实现
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;//即定义的枚举类
    //直接接收EmBusinessError参数构造业务异常
    public BusinessException(CommonError commonError){
        super();//调用父类exception的构造方法
        this.commonError=commonError;
    }
    //接收自定义errMsg的方式构造业务异常
    public BusinessException(CommonError commonError,String errMsg){
        super();
        this.commonError=commonError;
        this.commonError.setErrMsg(errMsg);
    }
    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
