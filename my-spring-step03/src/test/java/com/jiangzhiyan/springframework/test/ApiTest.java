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
        beanFactory.registerBeanDefinition("userService1",beanDefinition);
        beanFactory.registerBeanDefinition("userService2",beanDefinition);

        // 3.第一次获取bean
        UserService userService1 = (UserService) beanFactory.getBean("userService1");
        UserService userService2 = (UserService) beanFactory.getBean("userService2","test");
        userService1.query();
        userService2.query();

        //不是同一个bean
        System.out.println(userService1 == userService2);//false

        // 4.第二次获取bean,是从缓存获取的
        UserService userServiceSingleton1 = (UserService) beanFactory.getBean("userService1");
        UserService userServiceSingleton2 = (UserService) beanFactory.getBean("userService2");
        userServiceSingleton1.query();
        userServiceSingleton2.query();

        //同名称的bean是单例的
        System.out.println(userService1 == userServiceSingleton1);//true
        System.out.println(userService2 == userServiceSingleton2);//true
    }
}
