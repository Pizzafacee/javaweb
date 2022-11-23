package com.atguigu.myssm.filters;


import com.atguigu.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            //开启事务
            TransactionManager.startTransaction();
            //执行业务代码
            filterChain.doFilter(servletRequest, servletResponse);
            //提交事务
            TransactionManager.commitTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                TransactionManager.rollbackTransaction();
                System.out.println("回滚事务");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
