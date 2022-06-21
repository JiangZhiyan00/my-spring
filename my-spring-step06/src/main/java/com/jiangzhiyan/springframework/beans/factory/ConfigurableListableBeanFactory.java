package com.jiangzhiyan.springframework.beans.factory;

import com.jiangzhiyan.springframework.BeansException;
import com.jiangzhiyan.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.jiangzhiyan.springframework.beans.factory.config.BeanDefinition;
import com.jiangzhiyan.springframework.beans.factory.config.BeanPostProcessor;
import com.jiangzhiyan.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory,ListableBeanFactory, AutowireCapableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
