package atguigu.fruit.controllers;

import atguigu.fruit.dao.FruitDAO;
import atguigu.fruit.dao.impl.FruitDAOImpl;
import atguigu.fruit.pojo.Fruit;
import atguigu.myssm.myspringmvc.ViewBaseServlet;
import atguigu.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FruitController {
    private FruitDAO fruitDAO = new FruitDAOImpl();

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) throws ServletException, IOException {

        //3.执行更新
        fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        return "redirect:fruit.do";
    }

    private String edit(Integer fid, HttpServletRequest request) throws IOException, ServletException {

        Fruit fruit = fruitDAO.getFruitByFid(fid);
        request.setAttribute("fruit", fruit);
        //super.processTemplate("edit", request, response);
        return "edit";
    }

    private String del(Integer fid) throws IOException, ServletException {

        fruitDAO.delFruit(fid);
        return "redirect:fruit.do";

    }

    private String add(String fname, Integer price, Integer fcount, String remark) throws ServletException, IOException {
        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        fruitDAO.addFruit(fruit);
        return "redirect:fruit.do";

    }

    private String index(String oper, Integer pageNo, String keyword, HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (pageNo == null) {
            pageNo = 1;
        }
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            pageNo = 1;
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }

        // 重新更新当前页的值
        session.setAttribute("pageNo", pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword, pageNo);
        session.setAttribute("fruitList", fruitList);

        //总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount + 5 - 1) / 5;
        /*
        总记录条数       总页数
        1               1
        5               1
        6               2
        10              2
        11              3
        fruitCount      (fruitCount+5-1)/5
         */
        session.setAttribute("pageCount", pageCount);

        //此处的视图名称是 index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        //逻辑视图名称 ：   index
        //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：      /       index       .html
        //super.processTemplate("index", request, response);
        return "index";
    }
}
