package com.jiangzhiyan.springframework.factory.support;

import com.jiangzhiyan.springframework.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JiangZhiyan
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 存放bean的容器Map
     */
    private final Map<String, Object> singletonBeans = new HashMap<>();

    @Override
    public Object getSingletonBean(String beanName) {
        return singletonBeans.get(beanName);
    }

    /**
     * 添加bean到容器中
     * @param beanName bean名称
     * @param singletonBean bean对象
     */
    protected void addSingletonBean(String beanName, Object singletonBean) {
        singletonBeans.put(beanName, singletonBean);
    }
}
