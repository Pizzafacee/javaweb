package com.atguigu.servlet;

import com.atguigu.dao.FruitDao;
import com.atguigu.dao.impl.FruitDaoImpl;
import com.atguigu.entity.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QueryServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用dao查找出所有的fruit
        FruitDao fruitDao = new FruitDaoImpl();
        List<Fruit> fruits = fruitDao.queryAllFruit();
        req.setAttribute("fruits",fruits);
    }
}
