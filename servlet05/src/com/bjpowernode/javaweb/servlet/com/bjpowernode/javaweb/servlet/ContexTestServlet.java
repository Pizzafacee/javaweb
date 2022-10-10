package com.bjpowernode.javaweb.servlet.com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.Enumeration;

public class ContexTestServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        System.out.println(servletContext);
        Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String s = initParameterNames.nextElement();
            String initParameter = servletContext.getInitParameter(s);
            System.out.println(s + "=" + initParameter);
        }

        //获取contex path（获取上下文的根），这个方法在以后的应用中很重要
        String contextPath = servletContext.getContextPath();
        System.out.println(contextPath);

        //获取文件的绝对路径
        String realPath = servletContext.getRealPath("/index.html");
        System.out.println(realPath);

        //记录日志的方法
        servletContext.log("创建一个日志文件");

        //应用域
        //可以在应用域中存数据
        User user = new User("zhangsan",10);
        servletContext.setAttribute("userObj",user);
        //可以在应用域中取出数据
        Object userObj = servletContext.getAttribute("userObj");
        System.out.println(userObj);
        //可以移除应用域中的数据
       //servletContext.removeAttribute("userObj");

    }
}
