package atguigu.myssm.io.impl;

import atguigu.myssm.io.BeanFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String, Object> beanMap = new HashMap<>();

    public ClassPathXmlApplicationContext() {
        try {
            InputStream applicationContext = this.getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
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


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
