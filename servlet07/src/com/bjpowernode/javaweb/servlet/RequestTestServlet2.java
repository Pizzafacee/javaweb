package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class RequestTestServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
       /* Object time = req.getAttribute("nowTime");
        out.print(time);
        这样是获取不到另一个servlet中的请求域对象的*/
        //需要使用请求转发器对象

        //使用完转发器后
        Object time = req.getAttribute("nowTime");
        out.print(time);
    }
}
