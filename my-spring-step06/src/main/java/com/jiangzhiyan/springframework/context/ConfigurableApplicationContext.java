package com.jiangzhiyan.springframework.context;

import com.jiangzhiyan.springframework.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
