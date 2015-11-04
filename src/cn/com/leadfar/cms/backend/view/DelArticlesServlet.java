package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by tusizi on 2015/11/4.
 */
@WebServlet(name = "DelArticlesServlet")
public class DelArticlesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从界面获取删除文章的id
        String id = request.getParameter("id");
        if (id == null){
            //提示错误 forward 到错误页面
            request.setAttribute("error","无法删除文章，ID不允许为空");
            request.getRequestDispatcher("/backend/common/error.jsp").forward(request,response);
        }
        //从数据库中删除文章
        Connection conn = DBUtil.getConn();
        String sql = "delete from t_article where id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.executeUpdate();
            //什么意思？？？？？？
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            //为什么回滚，什么意思？？？？
            DBUtil.rollback(conn);
        }finally {
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
        //转向列表页面
        request.getRequestDispatcher("/backend/SearchArticlesServlet").forward(request,response);
    }
}
