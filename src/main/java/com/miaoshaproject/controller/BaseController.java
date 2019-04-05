package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/3 20:44
 * @software: IntelliJ IDEA
 * @file: BaseController
 * @description:
 */
//用于存放所有controller都会用到的部分，比如对异常的处理
public class BaseController {
    public static final String CONTENT_TYPE_FORMAT="application/x-www-form-urlencoded";

    //定义exceptionhandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex){
        Map<String,Object> response=new HashMap<>();
        if(ex instanceof BusinessException){
            BusinessException exception=(BusinessException)ex;
            response.put("errCode",exception.getErrCode());
            response.put("errMsg",exception.getErrMsg());
        }else{
            response.put("errCode",EmBusinessError.UNKOWN_ERROR.getErrCode());
            response.put("errMsg",EmBusinessError.UNKOWN_ERROR.getErrMsg());
        }
        return CommonReturnType.create(response,"fail");
    }
}
