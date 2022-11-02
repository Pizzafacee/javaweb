package atguigu.fruit.service.impl;

import atguigu.fruit.dao.FruitDAO;
import atguigu.fruit.dao.impl.FruitDAOImpl;
import atguigu.fruit.pojo.Fruit;
import atguigu.fruit.service.FruitService;

import java.util.List;

public class FruitServiceImpl implements FruitService {
    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        return fruitDAO.getFruitList(keyword, pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        return fruitDAO.getFruitCount(keyword);
    }

    @Override
    public void updateFruit(Fruit fruit) {

    }
}
