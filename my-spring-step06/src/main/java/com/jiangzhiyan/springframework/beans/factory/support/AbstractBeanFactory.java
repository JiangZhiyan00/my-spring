package com.jiangzhiyan.springframework.beans.factory.support;

import com.jiangzhiyan.springframework.BeansException;
import com.jiangzhiyan.springframework.beans.factory.config.BeanDefinition;
import com.jiangzhiyan.springframework.beans.factory.config.BeanPostProcessor;
import com.jiangzhiyan.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiangZhiyan
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        return this.doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return this.doGetBean(name,args);
    }

    /**
     * 暂时不做具体实现
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) this.getBean(name);
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

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 返回将应用于使用此工厂创建的bean的BeanPostProcessor集合
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}
