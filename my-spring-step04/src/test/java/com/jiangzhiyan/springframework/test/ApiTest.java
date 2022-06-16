package com.jiangzhiyan.springframework.test;

import com.jiangzhiyan.springframework.PropertyValue;
import com.jiangzhiyan.springframework.PropertyValues;
import com.jiangzhiyan.springframework.factory.config.BeanDefinition;
import com.jiangzhiyan.springframework.factory.config.BeanReference;
import com.jiangzhiyan.springframework.factory.support.DefaultListableBeanFactory;
import com.jiangzhiyan.springframework.test.bean.UserDao;
import com.jiangzhiyan.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void testBeanFactory(){
        // 1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册bean
        // 2.1注册userDao
        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));

        //2.2注册userService
        //2.2.1为userService设置属性[userId,userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userId","10002"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        //2.2.2userService注入bean容器
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
