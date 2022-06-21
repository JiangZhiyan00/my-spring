package com.jiangzhiyan.springframework.beans.factory;

import com.jiangzhiyan.springframework.BeansException;
import com.jiangzhiyan.springframework.beans.factory.config.BeanDefinition;
import com.jiangzhiyan.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory,ListableBeanFactory,HierarchicalBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
