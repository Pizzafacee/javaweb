package com.atguigu.myssm.myspringmvc;


import com.atguigu.myssm.ioc.BeanFactory;
import com.atguigu.myssm.utils.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@WebServlet("*.do")
public class DispatchServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    public DispatchServlet() {

    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = this.getServletContext();
        Object beanFactory = servletContext.getAttribute("beanFactory");
        BeanFactory factory = (BeanFactory) beanFactory;
        this.beanFactory = factory;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //发送/fruit.do->得到fruit
        String servletPath = req.getServletPath();
        //去掉/
        String substring = servletPath.substring(1);
        int i = substring.indexOf(".do");
        //获得fruit
        String controllerName = substring.substring(0, i);

        //从beanMap中获取FruitController对象
        Object o = beanFactory.getBean(controllerName);

        String operate = req.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }
        if (o != null) {
            Class<?> aClass = o.getClass();
            try {
                //Method declaredMethod = aClass.getDeclaredMethod(operate, HttpServletRequest.class, HttpServletResponse.class);
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    if (method.getName().equals(operate)) {
                        //获取方法参数
                        Parameter[] parameters = method.getParameters();
                        Object[] parameterObjects = new Object[parameters.length];
                        for (int j = 0; j < parameters.length; j++) {
                            Parameter parameter = parameters[j];
                            if ("request".equals(parameter.getName())) {
                                parameterObjects[j] = req;
                            } else if ("response".equals(parameter.getName())) {
                                parameterObjects[j] = resp;
                            } else if ("session".equals(parameter.getName())) {
                                parameterObjects[j] = req.getSession();
                            } else {
                                String reqParameter = req.getParameter(parameter.getName());
                                Object parameterObject = reqParameter;
                                if (reqParameter != null) {
                                    if ("java.lang.Integer".equals(parameter.getType().getName())) {
                                        parameterObject = Integer.parseInt(reqParameter);
                                    }
                                    parameterObjects[j] = parameterObject;
                                }
                            }
                        }
                        method.setAccessible(true);
                        Object invoke = method.invoke(o, parameterObjects);
                        if (invoke instanceof String) {
                            String returnValue = (String) invoke;
                            boolean redirect = returnValue.startsWith("redirect");
                            if (redirect) {
                                String substring1 = returnValue.substring("redirect:".length());
                                resp.sendRedirect(substring1);
                            } else {
                                super.processTemplate(returnValue, req, resp);
                            }
                        }
                    }
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
