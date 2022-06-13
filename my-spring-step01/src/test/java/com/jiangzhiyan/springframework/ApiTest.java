package com.jiangzhiyan.springframework;

import org.junit.Test;

public class ApiTest {

    @Test
    public void testBeanFactory(){
        //step01:定义Bean对象
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        //step02:注册Bean对象到容器中
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBeanDefinition("userService",beanDefinition);
        //step03:获取Bean对象
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.query();
    }
}
