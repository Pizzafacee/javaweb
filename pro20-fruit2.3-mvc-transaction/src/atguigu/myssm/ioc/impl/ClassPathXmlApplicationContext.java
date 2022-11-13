package atguigu.myssm.ioc.impl;

import atguigu.myssm.exception.DispatchServletException;
import atguigu.myssm.ioc.BeanFactory;
import atguigu.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String, Object> beanMap = new HashMap<>();
    private String path = "applicationContext.xml" ;
    public ClassPathXmlApplicationContext(){
        this("applicationContext.xml");
    }
    public ClassPathXmlApplicationContext(String path) {
        if(StringUtil.isEmpty(path)){
            throw new RuntimeException("IOC容器的配置文件没有指定...");
        }
        try {
            InputStream applicationContext = this.getClass().getClassLoader().getResourceAsStream(path);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //最后得到applicationContext的文件对象
            Document parse = documentBuilder.parse(applicationContext);
            //获取对应标签的节点
            NodeList bean = parse.getElementsByTagName("bean");
            for (int i = 0; i < bean.getLength(); i++) {
                //获取每一个节点
                Node item = bean.item(i);
                //如果是元素节点/标签节点
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    //强转成Element
                    Element element = (Element) item;
                    //可以获得fruit
                    String fruit = element.getAttribute("id");
                    //可以获得com.atguigu.controllers.FruitController
                    String aClass = element.getAttribute("class");
                    //反射获取FruitController对象
                    Class<?> aClass1 = Class.forName(aClass);
                    Object beanObject = aClass1.newInstance();
                    beanMap.put(fruit, beanObject);
                }
            }
            //组装bean之间的依赖关系
            for (int i = 0; i < bean.getLength(); i++) {
                Node node = bean.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanNode = (Element) node;
                    String beanId = beanNode.getAttribute("id");
                    NodeList childNodes = node.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node childNode = childNodes.item(j);
                        if (childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("property")) {
                            Element element = (Element) childNode;
                            String name = element.getAttribute("name");
                            String ref = element.getAttribute("ref");
                            Object beanObject = beanMap.get(beanId);
                            Object refObject = beanMap.get(ref);
                            Class<?> beanObjectClass = beanObject.getClass();
                            Field declaredField = beanObjectClass.getDeclaredField(name);
                            declaredField.setAccessible(true);
                            declaredField.set(beanObject, refObject);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatchServletException("DispatchServlet出错了...");
        }

    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
