package com.jiangzhiyan.springframework.context.support;

import com.jiangzhiyan.springframework.BeansException;
import com.jiangzhiyan.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.jiangzhiyan.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * {@link com.jiangzhiyan.springframework.context.ApplicationContext} 的基类
 * 应该支持多次调用{@link #refresh()}的实现
 * 每次创建一个新的内部bean工厂实例
 * 通常（但不一定），这样的上下文将由一组配置驱动，以从中加载BeanDefinition
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = this.createBeanFactory();
        this.loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
}
