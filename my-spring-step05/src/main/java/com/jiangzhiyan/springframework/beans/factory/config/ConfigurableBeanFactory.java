package com.jiangzhiyan.springframework.beans.factory.config;

import com.jiangzhiyan.springframework.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";
}
