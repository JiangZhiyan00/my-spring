package com.jiangzhiyan.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.jiangzhiyan.springframework.BeansException;
import com.jiangzhiyan.springframework.PropertyValue;
import com.jiangzhiyan.springframework.beans.factory.config.BeanDefinition;
import com.jiangzhiyan.springframework.beans.factory.config.BeanReference;
import com.jiangzhiyan.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.jiangzhiyan.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.jiangzhiyan.springframework.core.io.Resource;
import com.jiangzhiyan.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            InputStream inputStream = resource.getInputStream();
            this.doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("从"+resource+"解析xml文件异常",e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            this.loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            this.loadBeanDefinitions(this.getResourceLoader().getResource(location));
        }
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            //判断是否是xml元素
            if (!(item instanceof Element)){
                continue;
            }
            //判断是否是bean标签
            if (!"bean".equals(item.getNodeName())){
                continue;
            }
            //解析标签
            Element bean = (Element) item;
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            //获取Class,方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            //优先级id > name
            String beanName = StrUtil.isNotEmpty(id)? id:name;
            if (StrUtil.isEmpty(beanName)){
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            //定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            //读取属性并填充
            NodeList childNodes = bean.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node node = childNodes.item(j);
                if (!(node instanceof Element)){
                    continue;
                }
                if (!"property".equals(node.getNodeName())){
                    continue;
                }
                //解析标签:property
                Element property = (Element) node;
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                //获取属性值:引入对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef)? new BeanReference(attrRef): attrValue;
                //创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName,value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (this.getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("重复的bean名称["+beanName+"]");
            }
            this.getRegistry().registerBeanDefinition(beanName,beanDefinition);
        }
    }
}
