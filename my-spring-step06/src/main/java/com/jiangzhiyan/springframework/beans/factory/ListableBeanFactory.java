package com.jiangzhiyan.springframework.beans.factory;

import com.jiangzhiyan.springframework.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     * @param type bean类型
     * @return bean对象
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * @return 注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
