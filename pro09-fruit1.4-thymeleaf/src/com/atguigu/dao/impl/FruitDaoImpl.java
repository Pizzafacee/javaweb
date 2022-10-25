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
    public void updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname=?, price=?,fcount=?,remark=? where fid= ?";
        super.addOrUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
    }

    @Override
    public void deleteFruitById(long parseLong) {
        String sql = "delete from t_fruit where fid= ?";
        super.delete(sql,parseLong);
    }

    @Override
    public void addFruit(Fruit fruit) {
        super.addOrUpdate("insert into t_fruit values(0,?,?,?,?)",fruit.getFname(),fruit.getPrice(),fruit.getFcount());
    }
}
