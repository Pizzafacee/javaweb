package atguigu.myssm.myspringmvc;

import atguigu.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.do")
public class DispatchServlet extends ViewBaseServlet {
    private Map<String, Object> beanMap = new HashMap<>();

    public DispatchServlet() {

    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            //在初始化方法中解析xml文件
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
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //解析URL中的请求信息
        //发送/fruit.do->得到fruit
        String servletPath = req.getServletPath();
        //去掉/
        String substring = servletPath.substring(1);
        int i = substring.indexOf(".do");
        //获得fruit
        String controllerName = substring.substring(0, i);

        //从beanMap中获取FruitController对象
        Object o = beanMap.get(controllerName);

        String operate = req.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }
        if (o != null) {
            Class<?> aClass = o.getClass();
            try {
                Method declaredMethod = aClass.getDeclaredMethod(operate, HttpServletRequest.class, HttpServletResponse.class);
                if (declaredMethod != null) {
                    declaredMethod.setAccessible(true);
                    Object invoke = declaredMethod.invoke(o, req, resp);
                    if (invoke instanceof String) {
                        String returnValue = (String) invoke;
                        boolean redirect = returnValue.startsWith("redirect");
                        if(redirect){
                            String substring1 = returnValue.substring("redirect:".length());
                            resp.sendRedirect(substring1);
                        }else {
                            super.processTemplate(returnValue,req,resp);
                        }
                    }
                }

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
