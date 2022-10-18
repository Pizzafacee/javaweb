package com.atguigu.dao;

import com.atguigu.entity.Fruit;

import java.util.List;

public interface FruitDao {
    //获取所有的fruit
    List<Fruit> queryAllFruit();
    //根据id更新指定Fruit
    void updateFruitById(Integer id);
    //根据id删除指定Fruit
    void removeFruitById(Integer id);
    //添加一个Fruit
    void addFruit(Fruit fruit);
    //更新fruit
    public void updateFruit(Fruit fruit);
    //删除根据id
    void deleteFruitById(long parseLong);
}
