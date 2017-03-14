package com.quanr.Servlet;

/**
 * Created by kingsley.zhang on 2017/3/8.
 */

import com.quanr.Users.CheckUser;
import com.quanr.Users.User;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegistServlet extends HttpServlet {
    public static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RegistServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(request.getParameter("nickname"));
        try {
            if (CheckUser.regist(user) == CheckUser.FALSE) {
                LOGGER.info("注册失败");
                request.getRequestDispatcher("regist.jsp").forward(request, response);
            } else {
                LOGGER.info("{}注册成功",user.getUsername());// 注册 成功
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            LOGGER.warn("添加用户失败");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
