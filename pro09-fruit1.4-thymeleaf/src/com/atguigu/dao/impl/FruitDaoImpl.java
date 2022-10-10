package com.atguigu.dao.impl;

import com.atguigu.dao.FruitDao;
import com.atguigu.entity.Fruit;
import com.atguigu.mysqlSSM.basedao.BaseDao;

import java.util.List;

public class FruitDaoImpl extends BaseDao<Fruit> implements FruitDao {
    @Override
    public List<Fruit> queryAllFruit() {
        List<Fruit> fruits = super.executeQueryAll("select * from t_fruit");
        return fruits;
    }

    @Override
    public void updateFruitById(Integer id) {

    }

    @Override
    public void removeFruitById(Integer id) {

    }

    @Override
    public void addFruit(Fruit fruit) {

    }
}
