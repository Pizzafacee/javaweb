package com.atguigu.servlet;

import com.atguigu.dao.FruitDao;
import com.atguigu.dao.impl.FruitDaoImpl;
import com.atguigu.mysqlSSM.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String fid = req.getParameter("fid");
        long parseLong = Long.parseLong(fid);
        FruitDao fruitDao = new FruitDaoImpl();
        fruitDao.deleteFruitById(parseLong);
        resp.sendRedirect("index");
    }
}
