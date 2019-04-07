package com.miaoshaproject.error;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/3 19:40
 * @software: IntelliJ IDEA
 * @file: EmBusinessError
 * @description:
 */
public enum EmBusinessError implements CommonError {
    //通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKOWN_ERROR(10002,"未知错误"),
    //用户信息相关错误码定义20001
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_LOGIN_FAIL(20002,"用户名或密码错误"),
    USER_NOT_LOGIN(20003,"用户未登录"),
    //30000开头的表示交易信息错误
    STOCK_NOT_ENOUGH(30001,"库存不足"),
    ;

    EmBusinessError(int errCode,String errMsg){
        this.errCode=errCode;
        this.errMsg=errMsg;
    }
    private int errCode;
    private String errMsg;

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg=errMsg;
        return this;
    }
}
