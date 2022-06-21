package com.jiangzhiyan.springframework.beans.factory.support;

import com.jiangzhiyan.springframework.beans.factory.config.BeanDefinition;

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

    /**
     * 判断注册表中是否存在指定名称的bean注册信息
     * @param beanName bean名称
     * @return 存在true,不存在false
     */
    boolean containsBeanDefinition(String beanName);
}
