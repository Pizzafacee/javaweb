package com.bjpowernode.javaweb.servlet;

import com.bjpowernode.javaweb.servlet.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeptAdd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptno = req.getParameter("deptno");
        String dname =  req.getParameter("dname");
        String loc =  req.getParameter("loc");
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "insert into dept(deptno,dname,loc) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            ps.setString(2,dname);
            ps.setString(3,loc);
            int i = ps.executeUpdate();
            if(i == 1){
                resp.sendRedirect(req.getContextPath() + "/dept/list");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBUtil.close(null,ps,conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
