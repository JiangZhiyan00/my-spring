package com.jiangzhiyan.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

public interface Resource {

    /**
     * 获取资源输入流
     * @return 资源的输入流
     */
    InputStream getInputStream() throws IOException;
}
