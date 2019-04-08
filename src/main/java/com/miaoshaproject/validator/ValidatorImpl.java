package com.miaoshaproject.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/5 20:18
 * @software: IntelliJ IDEA
 * @file: ValidatorImpl
 * @description:
 */
@Component
public class ValidatorImpl implements InitializingBean {
    private Validator validator;//创建一个javax的validator对象
    @Override
    public void afterPropertiesSet() throws Exception {
        //通过Validation的默认工厂构造方法创建一个validator对象
        this.validator=Validation.buildDefaultValidatorFactory().getValidator();
    }
    //传入实现检验方法并返回校验结果
    public ValidationResult validate(Object bean){
        final ValidationResult result=new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet=validator.validate(bean);
        if(constraintViolationSet.size()>0){
            result.setHasErr(true);
            constraintViolationSet.forEach(constraintViolation->{
                String errMsg=constraintViolation.getMessage();
                String propertyName=constraintViolation.getPropertyPath().toString();
                result.getErrMsgMap().put(propertyName,errMsg);
            });
        }
        return result;
    }
}
