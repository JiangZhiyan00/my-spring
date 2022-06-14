package com.jiangzhiyan.springframework.factory.config;

/**
 * @author JiangZhiyan
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例bean
     * @param beanName bean名称
     * @return 单例bean
     */
    Object getSingletonBean(String beanName);
}
