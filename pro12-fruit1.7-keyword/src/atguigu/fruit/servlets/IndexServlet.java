package atguigu.fruit.servlets;


import atguigu.fruit.dao.FruitDAO;
import atguigu.fruit.dao.impl.FruitDAOImpl;
import atguigu.fruit.pojo.Fruit;
import atguigu.myssm.myspringmvc.ViewBaseServlet;
import atguigu.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Integer pageNo = 1;
        String keyword = "";
        //判断是否从有关键字的查询表单过来的请求，如果是，那么页数为起始页1,
        String oper = request.getParameter("oper");
        if (oper != null && "search".equals(oper)) {
            keyword = request.getParameter("keyword");
            if (keyword == null) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            //如果不是，说明是从其他地方比如 上页 下一页过来的
            String pageNo1 = request.getParameter("pageNo");
            pageNo = Integer.parseInt(pageNo1);
            Object keyword1 = session.getAttribute("keyword");
            if (keyword1 == null) {
                keyword = "";
            }
            keyword = (String) keyword1;
        }

        session.setAttribute("pageNo",pageNo);
        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword, pageNo);
        session.setAttribute("fruitList",fruitList);

        int fruitCount = fruitDAO.getFruitCount(keyword);
        int pageCount = (fruitCount+5-1)/5;
        session.setAttribute("pageCount",pageCount);

        super.processTemplate("index",request,response);


//        request.setCharacterEncoding("UTF-8");
//        HttpSession session = request.getSession() ;
//        Integer pageNo = 1 ;
//


//        String oper = request.getParameter("oper");
//        //如果oper!=null 说明 通过表单的查询按钮点击过来的
//        //如果oper是空的，说明 不是通过表单的查询按钮点击过来的
//
//        String keyword = null ;
//        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){
//            //说明是点击表单查询发送过来的请求
//            //此时，pageNo应该还原为1 ， keyword应该从请求参数中获取
//            pageNo = 1 ;
//            keyword = request.getParameter("keyword");
//            if(StringUtil.isEmpty(keyword)){
//                keyword = "" ;
//            }
//            session.setAttribute("keyword",keyword);
//        }else{
//            //说明此处不是点击表单查询发送过来的请求（比如点击下面的上一页下一页或者直接在地址栏输入网址）
//            //此时keyword应该从session作用域获取
//            String pageNoStr = request.getParameter("pageNo");
//            if(StringUtil.isNotEmpty(pageNoStr)){
//                pageNo = Integer.parseInt(pageNoStr);
//            }
//            Object keywordObj = session.getAttribute("keyword");
//            if(keywordObj!=null){
//                keyword = (String)keywordObj ;
//            }else{
//                keyword = "" ;
//            }
//        }
//
//        session.setAttribute("pageNo",pageNo);
//
//        FruitDAO fruitDAO = new FruitDAOImpl();
//        List<Fruit> fruitList = fruitDAO.getFruitList(keyword , pageNo);
//
//        session.setAttribute("fruitList",fruitList);
//
//        //总记录条数
//        int fruitCount = fruitDAO.getFruitCount(keyword);
//        //总页数
//        int pageCount = (fruitCount+5-1)/5 ;
//        /*
//        总记录条数       总页数
//        1               1
//        5               1
//        6               2
//        10              2
//        11              3
//        fruitCount      (fruitCount+5-1)/5
//         */
//        session.setAttribute("pageCount",pageCount);
//
//        //此处的视图名称是 index
//        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
//        //逻辑视图名称 ：   index
//        //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
//        //所以真实的视图名称是：      /       index       .html
//        super.processTemplate("index",request,response);
    }
}
