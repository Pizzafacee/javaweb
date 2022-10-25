package com.atguigu.fruit.servlets;

import com.atguigu.fruit.dao.FruitDao;
import com.atguigu.fruit.dao.impl.FruitDaoImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String fname = req.getParameter("fname");
        String price = req.getParameter("price");
        String fcount = req.getParameter("fcount");
        String remark = req.getParameter("remark");
        Fruit fruit = new Fruit();
        fruit.setFid(0);
        fruit.setFname(fname);
        fruit.setPrice(Integer.parseInt(price));
        fruit.setFcount(Integer.parseInt(fcount));
        fruit.setRemark(remark);
        FruitDao fruitDao = new FruitDaoImpl();
        fruitDao.addFruit(fruit);
        resp.sendRedirect("index");
    }
}
