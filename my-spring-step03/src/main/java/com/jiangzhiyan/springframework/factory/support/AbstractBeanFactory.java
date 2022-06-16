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
        return this.doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return this.doGetBean(name,args);
    }

    protected <T> T doGetBean(String name, final Object[] args) {
        Object bean = this.getSingletonBean(name);
        if (bean != null) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = this.getBeanDefinition(name);
        return (T) this.createBean(name, beanDefinition, args);
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
     * @param beanDefinition bean信息
     * @param args bean的构造方法参数
     * @return 实例化后的bean
     */
    protected abstract Object createBean(String name, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
