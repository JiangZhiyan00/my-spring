package com.jiangzhiyan.springframework.core.io;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import com.jiangzhiyan.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource {

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path,null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path,"path不能为null");
        this.path = path;
        this.classLoader = classLoader == null? ClassUtils.getDefaultClassLoader():classLoader;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (inputStream == null){
            throw new FileNotFoundException(this.path + "不存在,无法加载");
        }
        return inputStream;
    }
}
