package com.jiangzhiyan.springframework.factory.support;

import com.jiangzhiyan.springframework.BeansException;
import com.jiangzhiyan.springframework.factory.config.BeanDefinition;

/**
 * @author JiangZhiyan
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) throws BeansException{
        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("bean实例化失败", e);
        }
        this.addSingletonBean(name, bean);
        return bean;
    }
}
