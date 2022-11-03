package atguigu.fruit.service.impl;

import atguigu.fruit.dao.FruitDAO;
import atguigu.fruit.dao.impl.FruitDAOImpl;
import atguigu.fruit.pojo.Fruit;
import atguigu.fruit.service.FruitService;

import java.util.List;

public class FruitServiceImpl implements FruitService {
    private FruitDAO fruitDao;

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        return fruitDao.getFruitList(keyword, pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDao.addFruit(fruit);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return fruitDao.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDao.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        return fruitDao.getFruitCount(keyword);
    }

    @Override
    public void updateFruit(Fruit fruit) {

    }
}
