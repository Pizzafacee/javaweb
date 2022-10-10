package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

public class RequestTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getParameterMap();//获取 map()
        Enumeration<String> parameterNames = req.getParameterNames();//获取所有的key
        String parameter = req.getParameter("");//通过Key获取第一个value
        String[] parameterValues = req.getParameterValues("");//通过key获取所有的value

        //请求域对象,可以和应用域对象对照学习
        req.setAttribute("",null);
        req.getAttribute("");
        req.removeAttribute("");

    }
}
