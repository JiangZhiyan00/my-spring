package com.jiangzhiyan.springframework.beans.factory;

import com.jiangzhiyan.springframework.BeansException;

/**
 * @author JiangZhiyan
 */
public interface BeanFactory {

    /**
     * 根据bean名称获取bean对象
     * @param name bean名称
     * @return bean对象
     */
    Object getBean(String name) throws BeansException;

    /**
     * 根据bean名称及构造函数入参获取bean对象
     * @param name bean名称
     * @param args 构造函数入参
     * @return bean对象
     */
    Object getBean(String name,Object... args) throws BeansException;

    /**
     * 根据bean的名称和类型获取bean对象
     * @param name bean名称
     * @param requiredType bean类型
     * @return bean对象
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
