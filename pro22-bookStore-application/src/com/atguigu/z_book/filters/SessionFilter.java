package com.atguigu.z_book.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = {"*.do", "*.html"},
        initParams = {@WebInitParam(name = "bai",
                value = "/pro25/page.do?operate=page&page=user/login,/pro25/user.do?null")})
public class SessionFilter implements Filter {
    private List<String> baiList = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String bai = filterConfig.getInitParameter("bai");
        String[] split = bai.split(",");
        List<String> strings = Arrays.asList(split);
        baiList = strings;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        System.out.println("request.getRequestURI()=" + httpServletRequest.getRequestURI());
        System.out.println("request.getQueryString = " + httpServletRequest.getQueryString());

        String requestURI = httpServletRequest.getRequestURI();
        String queryString = httpServletRequest.getQueryString();
        String baiStr = requestURI + "?" + queryString;
        if (baiList.contains(baiStr)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpSession session = httpServletRequest.getSession();
            String currUser = (String) session.getAttribute("currUser");
            if (currUser != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpServletResponse.sendRedirect("page.do?operate=page&page=user/login");
            }
        }

    }

    @Override
    public void destroy() {

    }
}
