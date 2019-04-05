package com.miaoshaproject.error;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/3 19:37
 * @software: IntelliJ IDEA
 * @file: CommonError
 * @description:
 */
public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);

}
