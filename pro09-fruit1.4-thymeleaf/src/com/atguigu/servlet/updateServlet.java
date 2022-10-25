package com.atguigu.servlet;

import com.atguigu.dao.FruitDao;
import com.atguigu.dao.impl.FruitDaoImpl;
import com.atguigu.entity.Fruit;
import com.atguigu.mysqlSSM.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class updateServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String fname = req.getParameter("fname");
        String price = req.getParameter("price");
        String fcount = req.getParameter("fcount");
        String remark = req.getParameter("remark");
        String fid = req.getParameter("fid");
        Fruit fruit = new Fruit();
        fruit.setFname(fname);
        fruit.setPrice(Integer.parseInt(price));
        fruit.setFcount(Integer.parseInt(fcount));
        fruit.setRemark(remark);
        FruitDao fruitDao = new FruitDaoImpl();
        fruitDao.updateFruit(fruit);
        resp.sendRedirect("index");
    }
}
