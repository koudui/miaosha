package com.miaoshaproject.response;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/3 19:14
 * @software: IntelliJ IDEA
 * @file: CommonReturnType
 * @description:
 */
public class CommonReturnType {
    //表明对应请求的放回处理结果success或fail
    private String status;
    //若status为success，则data里面返回前端json需要的数据
    //若status为fail，则data内使用通用的错误码格式
    private Object data;

    //定义一个通用的创建方法
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }
    public static CommonReturnType create(Object result,String status){
        CommonReturnType type=new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
