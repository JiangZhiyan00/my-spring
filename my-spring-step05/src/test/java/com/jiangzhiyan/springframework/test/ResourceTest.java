package com.jiangzhiyan.springframework.test;

import cn.hutool.core.io.IoUtil;
import com.jiangzhiyan.springframework.core.io.DefaultResourceLoader;
import com.jiangzhiyan.springframework.core.io.Resource;
import com.jiangzhiyan.springframework.core.io.ResourceLoader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ResourceTest {

    private ResourceLoader resourceLoader;

    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void testClassPath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:import.properties");
        InputStream inputStream = resource.getInputStream();
        System.out.println(IoUtil.readUtf8(inputStream));
    }

    @Test
    public void testFileSystem() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/import.properties");
        InputStream inputStream = resource.getInputStream();
        System.out.println(IoUtil.readUtf8(inputStream));
    }

    @Test
    public void testUrl() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/JiangZhiyan00/my-spring/blob/main/my-spring-step05/src/test/resources/import.properties");
        InputStream inputStream = resource.getInputStream();
        System.out.println(IoUtil.readUtf8(inputStream));
    }
}
