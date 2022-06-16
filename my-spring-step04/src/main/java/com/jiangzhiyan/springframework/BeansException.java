package com.jiangzhiyan.springframework;

/**
 * 定义Bean异常
 * @author JiangZhiyan
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
