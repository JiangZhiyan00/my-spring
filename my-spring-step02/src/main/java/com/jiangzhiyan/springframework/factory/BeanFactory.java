package com.jiangzhiyan.springframework.factory;

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
}
