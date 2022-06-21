package com.jiangzhiyan.springframework.core.io;

public interface ResourceLoader {

    /**
     * classpath前缀"classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 获取资源
     * @param location 资源地址
     */
    Resource getResource(String location);
}
