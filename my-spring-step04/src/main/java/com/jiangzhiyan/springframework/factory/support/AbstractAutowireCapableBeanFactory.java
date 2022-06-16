package com.jiangzhiyan.springframework.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.jiangzhiyan.springframework.BeansException;
import com.jiangzhiyan.springframework.PropertyValue;
import com.jiangzhiyan.springframework.PropertyValues;
import com.jiangzhiyan.springframework.factory.config.BeanDefinition;
import com.jiangzhiyan.springframework.factory.config.BeanReference;

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
            // 给bean填充属性
            this.applyPropertyValues(name,bean,beanDefinition);
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
    protected Object createBeanInstance(String name, BeanDefinition beanDefinition, Object[] args) {
        Constructor<?> ctorToUse = null;
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

    /**
     * 给bean填充属性
     * @param beanName bean名称
     * @param bean 实例化的bean
     * @param beanDefinition bean信息,包含bean的属性值
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    // A依赖B,递归填充bean属性
                    value = this.getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean,name,value);
            }
        }catch (Exception e){
            throw new BeansException("填充bean的属性值异常:" + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
