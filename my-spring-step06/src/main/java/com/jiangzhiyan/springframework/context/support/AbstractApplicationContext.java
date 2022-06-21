package com.jiangzhiyan.springframework.context.support;

import com.jiangzhiyan.springframework.BeansException;
import com.jiangzhiyan.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.jiangzhiyan.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.jiangzhiyan.springframework.beans.factory.config.BeanPostProcessor;
import com.jiangzhiyan.springframework.context.ConfigurableApplicationContext;
import com.jiangzhiyan.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * <p>抽象应用上下文</p>
 * {@link com.jiangzhiyan.springframework.context.ApplicationContext} 接口的抽象实现。
 * 不强制要求用于配置的存储类型；简单地实现通用的上下文功能。使用模板方法设计模式，需要具体的子类来实现抽象方法
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        // 1. 创建BeanFactory，并加载BeanDefinition
        this.refreshBeanFactory();

        // 2. 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = this.getBeanFactory();

        // 3. 在Bean实例化之前，执行BeanFactoryPostProcessor(Invoke factory processors registered as beans in the context.)
        this.invokeBeanFactoryPostProcessors(beanFactory);

        // 4. BeanPostProcessor需要提前于其他Bean对象实例化之前执行注册操作
        this.registerBeanPostProcessors(beanFactory);

        // 5. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return this.getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return this.getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return this.getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return this.getBeanFactory().getBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return this.getBeanFactory().getBean(name,requiredType);
    }
}
