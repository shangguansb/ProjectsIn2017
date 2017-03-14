package com.quanr.Servlet;

/**
 * Created by kingsley.zhang on 2017/3/8.
 */

import com.google.common.base.Strings;
import com.quanr.Users.CheckUser;
import com.quanr.Users.User;
import com.quanr.Utils.QunarUtils;
import com.quanr.dao.UserDao;
import com.quanr.dao.imlp.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserDao userDao = new UserDaoImpl();
        User userTemp = userDao.queryUserByName(username);
        if (CheckUser.login(user) == CheckUser.FALSE) {             //  登录失败
            response.sendRedirect("regist.jsp");
        } else {                                                   // 登录 成功
            if (!Strings.isNullOrEmpty(username) && userTemp != null) {
                QunarUtils.removeCookies(request, response);
                Cookie cookie = new Cookie(username, password);
                cookie.setMaxAge(24 * 60 * 60 * 7);
                response.addCookie(cookie);
                request.setAttribute("nickname", userTemp.getNickname());
                response.sendRedirect("welcome.jsp");
            } else {
                response.sendRedirect("regist.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
