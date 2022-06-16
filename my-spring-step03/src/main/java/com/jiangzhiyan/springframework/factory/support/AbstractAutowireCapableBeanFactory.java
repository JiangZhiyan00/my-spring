package com.jiangzhiyan.springframework.factory.support;

import com.jiangzhiyan.springframework.BeansException;
import com.jiangzhiyan.springframework.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author JiangZhiyan
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    /**
     * 默认Cglib实例化策略
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition,Object[] args) throws BeansException{
        Object bean;
        try {
            bean = this.createBeanInstance(name,beanDefinition,args);
        } catch (Exception e) {
            throw new BeansException("bean实例化失败", e);
        }
        this.addSingletonBean(name, bean);
        return bean;
    }

    /**
     * 实例化bean对象的处理
     * @param name bean名称
     * @param beanDefinition bean信息
     * @param args 构造函数参数
     * @return bean对象
     */
    private Object createBeanInstance(String name, BeanDefinition beanDefinition, Object[] args) {
        Constructor ctorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] constructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> ctor : constructors) {
            //TODO 此处暂时先用参数长度判断,实际还需要判断参数类型
            if (args != null && args.length == ctor.getParameters().length){
                ctorToUse = ctor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition,name,ctorToUse,args);
    }
}
