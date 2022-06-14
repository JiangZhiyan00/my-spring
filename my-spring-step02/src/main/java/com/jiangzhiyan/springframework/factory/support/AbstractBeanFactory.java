package com.jiangzhiyan.springframework.factory.support;

import com.jiangzhiyan.springframework.BeansException;
import com.jiangzhiyan.springframework.factory.BeanFactory;
import com.jiangzhiyan.springframework.factory.config.BeanDefinition;

/**
 * @author JiangZhiyan
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = this.getSingletonBean(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = this.getBeanDefinition(name);
        return this.createBean(name, beanDefinition);
    }

    /**
     * 获取bean注册信息对象
     * @param name bean的名称
     * @return bean注册信息
     */
    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    /**
     * 创建bean对象
     * @param name bean名称
     * @param beanDefinition bean的注册信息
     * @return 新建的bean对象
     */
    protected abstract Object createBean(String name, BeanDefinition beanDefinition) throws BeansException;
}
