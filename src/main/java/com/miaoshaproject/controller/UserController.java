package com.miaoshaproject.controller;

import com.alibaba.druid.util.StringUtils;
import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * encoding: utf-8
 *
 * @Author: kou dui
 * @Date: 2019/4/1 22:49
 * @software: IntelliJ IDEA
 * @file: UserController
 * @description:
 */
@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowedHeaders = {"*"},allowCredentials = "true") //springboot前端请求的跨域问题
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/get")
    @ResponseBody
//    public UserVO getUser(@RequestParam(name="id") Integer id){
//        //调用service服务获取对应id的用户对象并返回给前端
//        UserModel userModel=userService.getUserById(id);
//        //将核心领域模型用户对象转换为可供前端UI使用的viewobject，避免前端拿到不应该拿到的
//        return convertFromModel(userModel);
//    }
    //定义通用的返回对象，返回正确的信息
    public CommonReturnType getUser(@RequestParam(name="id") Integer id) throws Exception {
        UserModel userModel=userService.getUserById(id);
        //如果获取的对象不存在，则抛出自定义异常
        if(userModel==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
//            throw new Exception();//用于测试不抛出的异常不是BusinessException的情况
        }
        UserVO userVO=convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }
    private UserVO convertFromModel(UserModel userModel){
        UserVO userVO=new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }

    //用户登录接口
    @RequestMapping(value = "/login",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMAT})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name="telphone") String telphone,
                                  @RequestParam(name="password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if(org.apache.commons.lang3.StringUtils.isEmpty(telphone)||
                org.apache.commons.lang3.StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //登录流程
        UserModel userModel=userService.validateLogin(telphone,this.encodeByMd5(password));
        //将登录成功凭证加到用户登录成功的session内
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);
        return CommonReturnType.create(null);
    }


    //用户注册接口
    @RequestMapping(value = "/register",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMAT})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name="telphone") String telphone,
                                     @RequestParam(name="name") String name,
                                     @RequestParam(name="gender") String gender,
                                     @RequestParam(name="otpCode") String otpCode,
                                     @RequestParam(name="age") String age,
                                     @RequestParam(name="password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //判断验证码是否匹配
        String inSessionOtpCode=(String)this.httpServletRequest.getSession().getAttribute(telphone);
        if(!StringUtils.equals(inSessionOtpCode,otpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"输入的短信验证码错误");
        }
        //用户注册流程
        UserModel userModel=new UserModel();
        userModel.setAge(Integer.parseInt(age));
        userModel.setName(name);
        userModel.setGender(new Byte(String.valueOf(gender)));
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(this.encodeByMd5(password));

        userService.register(userModel);
        return CommonReturnType.create(null);
    }
    public String encodeByMd5(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder=new BASE64Encoder();
        String encrpt=base64Encoder.encode(md5.digest(password.getBytes("utf-8")));
        return encrpt;
    }



    //用户获取otp短信验证码接口
    @RequestMapping(value = "/getotp",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMAT})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name="telphone") String telphone){
        //按照一定的规则生成otp验证码
        Random random=new Random();
        int otpRand=random.nextInt(99999);
        otpRand+=10000;
        String otpCode=String.valueOf(otpRand);
        //将otp验证码和用户telphone绑定,使用session将telphone和otpcode绑定
        httpServletRequest.getSession().setAttribute(telphone,otpCode);
        System.out.println("telphone="+telphone+" &otpCode="+otpCode);
        return CommonReturnType.create(null);
        //将otp验证码通过短信通道发送给用户
    }


}
