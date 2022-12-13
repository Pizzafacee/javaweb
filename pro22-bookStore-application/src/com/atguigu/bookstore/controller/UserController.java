package com.atguigu.bookstore.controller;

import com.atguigu.bookstore.pojo.Cart;
import com.atguigu.bookstore.pojo.User;
import com.atguigu.bookstore.service.CartItemService;
import com.atguigu.bookstore.service.UserService;
import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController {
    private UserService userService;
    private CartItemService cartItemService ;

    public String login(String uname, String pwd, HttpSession session) {
        User user = userService.login(uname, pwd);
        if (user != null) {
            session.setAttribute("currUser",user);
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            return "redirect:book.do";
        }
        return "user/login";
    }

    public String regist(String verifycode, String uname, String pwd, String email, HttpSession session, HttpServletResponse httpServletResponse) throws IOException {
        Object attribute = session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(attribute==null||!verifycode.equals(attribute)){
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = httpServletResponse.getWriter();
            writer.println("<script language='javascript'>alert('验证码不正确！');</script>");
            return "user/regist";
        }
        else {
            userService.regist(new User(uname , pwd , email,0));
            return "user/login";
        }
    }
}
