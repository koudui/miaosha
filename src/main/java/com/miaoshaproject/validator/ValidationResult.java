package com.miaoshaproject.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/5 20:06
 * @software: IntelliJ IDEA
 * @file: ValidationResult
 * @description:
 */
public class ValidationResult {
    //是否有错误信息
    private Boolean hasErr=false;
    //返回具体的错误消息
    private Map<String,String> errMsgMap=new HashMap<>();

    public Boolean getHasErr() {
        return hasErr;
    }

    public void setHasErr(Boolean hasErr) {
        this.hasErr = hasErr;
    }

    public Map<String, String> getErrMsgMap() {
        return errMsgMap;
    }

    public void setErrMsgMap(Map<String, String> errMsgMap) {
        this.errMsgMap = errMsgMap;
    }
    //通用的方法用于返回结果
    public String getErrMsg(){
        return StringUtils.join(errMsgMap.values().toArray(),",");
    }
}
