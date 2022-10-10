package com.bjpowernode.javaweb.servlet;

import com.bjpowernode.javaweb.servlet.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        out.print("                 <!DOCTYPE html>");
        out.print(" <html lang='en'>");
        out.print(" <head>");
        out.print("     <meta charset='UTF-8'>");
        out.print("     <title>list</title>");
        out.print(" </head>");
        out.print(" <body>");
        out.print(" <!--新建list表，包含个部门信息-->");
        out.print(" <h2 align='center'>部门清单</h2>");
        out.print(" <hr>");
        out.print(" <table border='1px' align='center' width='50%'>");
        out.print("     <tr>");
        out.print("         <th>deptno</th>");
        out.print("         <th>dname</th>");
        out.print("         <th>loc</th>");
        out.print("         <th>操作</th>");
        out.print("     </tr>");
        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno,dname,loc from dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                out.print("     <tr>");
                out.print("         <th>" + deptno + "</th>");
                out.print("         <th>" + dname + "</th>");
                out.print("         <th>" + loc + "</th>");
                out.print("         <th>");
                out.print("             <a href=''>修改</a>");
                out.print("             <a href=''>删除</a>");
                out.print("             <a href=''>详情</a>");
                out.print("         </th>");
                out.print("     </tr>");


            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtil.close(rs, ps, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        out.print(" </table>");
        out.print("     <hr>");
        out.print("     <a href='/oa/add.html'>增加信息</a>");
        out.print(" </body>");
        out.print(" </html>");
    }

}
