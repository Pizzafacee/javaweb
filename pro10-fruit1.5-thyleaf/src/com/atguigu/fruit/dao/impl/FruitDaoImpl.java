package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDao;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.baseDao.BaseDao;

import java.util.List;

public class FruitDaoImpl extends BaseDao<Fruit> implements FruitDao {
    @Override
    public List<Fruit> getFruitList() {
        String sql = "select * from t_fruit";
        List<Fruit> fruitList = super.executeQuery(sql);
        return fruitList;
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return null;
    }

    @Override
    public void updateFruit(Fruit fruit) {

    }

    @Override
    public void delFruit(Integer fid) {

    }

    @Override
    public void addFruit(Fruit fruit) {

    }
}
