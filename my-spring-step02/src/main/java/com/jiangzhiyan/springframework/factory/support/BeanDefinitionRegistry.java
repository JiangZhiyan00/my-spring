package com.jiangzhiyan.springframework.factory.support;

import com.jiangzhiyan.springframework.factory.config.BeanDefinition;

/**
 * @author JiangZhiyan
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册BeanDefinition
     * @param beanName bean的名称
     * @param beanDefinition bean的注册信息
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
