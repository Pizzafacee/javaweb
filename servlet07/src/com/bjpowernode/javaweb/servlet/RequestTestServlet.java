package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class RequestTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();*/
        Date nowTime = new Date();
        req.setAttribute("nowTime",nowTime);
        /*Object time = req.getAttribute("nowTime");
        out.print(time);*/

        //使用请求转发器对象
        //获取请求转发器对象
       RequestDispatcher requestDispatcher = req.getRequestDispatcher("/request02");//形势参数为另一个servlet的路径
        //执行转发
       requestDispatcher.forward(req,resp);  //将当前servlet中的re quest和response转发到另一个serv中，完成资源的跳转
    }
}
