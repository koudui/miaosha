package com.miaoshaproject;

import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
//指定扫描包，参数是包名的字符串数组
@SpringBootApplication(scanBasePackages ={"com.miaoshaproject"})
//相当于@ResponseBody ＋@Controller合在一起的作用
@RestController
//通过此注解指定mybatis的路径，即可完成对mybatis接口的扫描
@MapperScan("com.miaoshaproject.dao")
public class App 
{
    //当需要从Bean工厂获取一个Bean时，Spring会自动装配该Bean中标记位@Autowired的元素，默认按照类型匹配
    @Autowired
    private UserDOMapper userDOMapper;

    //处地址映射的注解，表示可通过value访问
    @RequestMapping("/")
    //此方法只是用作测试，与主要功能没有关系
    public String home(){
        UserDO userDO=userDOMapper.selectByPrimaryKey(1);
        if(userDO==null){
            return "用户不存在";
        }else{
            return userDO.getName();
        }
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);
    }
}
