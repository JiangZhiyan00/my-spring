package com.jiangzhiyan.springframework.test;

import com.jiangzhiyan.springframework.factory.config.BeanDefinition;
import com.jiangzhiyan.springframework.factory.support.DefaultListableBeanFactory;
import com.jiangzhiyan.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void testBeanFactory(){
        // 1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        // 3.第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.query();

        // 4.第二次获取bean,是从缓存获取的
        UserService userServiceSingleton = (UserService) beanFactory.getBean("userService");
        userServiceSingleton.query();

        //同类型的bean是单例的
        System.out.println(userService == userServiceSingleton);//true
    }
}
