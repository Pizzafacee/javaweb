package com.atguigu.servlet;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.servlet.KaptchaServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/kaptcha.do")
public class KaptchaServletDemo extends KaptchaServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object attribute = session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println("obj="+attribute.toString());
    }
}
