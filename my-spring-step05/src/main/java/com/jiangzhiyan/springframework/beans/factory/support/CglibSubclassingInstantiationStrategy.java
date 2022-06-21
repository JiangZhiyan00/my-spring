package com.jiangzhiyan.springframework.beans.factory.support;

import com.jiangzhiyan.springframework.BeansException;
import com.jiangzhiyan.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * Cglib实例化策略
 * @author JiangZhiyan
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object... args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (ctor == null){
            return enhancer.create();
        }
        return enhancer.create(ctor.getParameterTypes(),args);
    }
}
