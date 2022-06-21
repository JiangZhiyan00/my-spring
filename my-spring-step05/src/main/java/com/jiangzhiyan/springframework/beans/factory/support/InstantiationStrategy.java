package com.jiangzhiyan.springframework.beans.factory.support;

import com.jiangzhiyan.springframework.BeansException;
import com.jiangzhiyan.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略接口
 * @author JiangZhiyan
 */
public interface InstantiationStrategy {

    /**
     * 实例化方法
     * @param beanDefinition bean信息对象
     * @param beanName bean名称
     * @param ctor 构造方法
     * @param args 实例化bean的入参
     * @return bean对象
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName,
                       Constructor ctor, Object... args) throws BeansException;
}
