package com.atguigu.myssm.listeners;


import com.atguigu.myssm.ioc.BeanFactory;
import com.atguigu.myssm.ioc.impl.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //在servlet初始化的时候创建bean容器
        //先得到servlet上下文对象
        ServletContext servletContext = servletContextEvent.getServletContext();
        //获取初始化参数
        String path = servletContext.getInitParameter("contextConfigLocation");
        //创建ioc容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        servletContext.setAttribute("beanFactory", beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
