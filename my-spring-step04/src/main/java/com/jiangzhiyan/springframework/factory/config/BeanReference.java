package com.jiangzhiyan.springframework.factory.config;

/**
 * Bean的引用
 * @author JiangZhiyan
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
