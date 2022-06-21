package com.jiangzhiyan.springframework.test;

import com.jiangzhiyan.springframework.beans.factory.BeanFactory;
import com.jiangzhiyan.springframework.beans.factory.ListableBeanFactory;
import com.jiangzhiyan.springframework.beans.factory.config.BeanDefinition;
import com.jiangzhiyan.springframework.beans.factory.support.BeanDefinitionReader;
import com.jiangzhiyan.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.jiangzhiyan.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.jiangzhiyan.springframework.core.io.DefaultResourceLoader;
import com.jiangzhiyan.springframework.core.io.Resource;
import com.jiangzhiyan.springframework.core.io.ResourceLoader;
import com.jiangzhiyan.springframework.test.bean.UserDao;
import com.jiangzhiyan.springframework.test.bean.UserService;
import org.junit.Before;
import org.junit.Test;

public class BeanTest {

    private ResourceLoader resourceLoader;

    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void testXml(){
        // 初始化Bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        //获取bean调用方法
        UserDao userDao = (UserDao) beanFactory.getBean("userDao");
        System.out.println(userDao.queryUsername("10002"));

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
