package com.quanr.Users;

import com.quanr.dao.UserDao;
import com.quanr.dao.imlp.UserDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kingsley.zhang on 2017/3/8.
 */
public class UserFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Filter.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Cookie[] cookies = req.getCookies();
        LOGGER.info("into filter");
        if (cookies != null) {
            LOGGER.info("into if");
            for (Cookie cookie : cookies) {
                UserDao userDao = new UserDaoImpl();
                User userTemp = userDao.queryUserByName(cookie.getName());
                if (userTemp != null
                        && cookie.getValue().equals(userTemp.getPassword())) {
                    req.setAttribute("nickname", userTemp.getNickname());
                    req.getRequestDispatcher("welcome.jsp").forward(req, resp);
                    LOGGER.info("用户 {} 已登录，跳转欢迎界面", cookie.getValue());
                    return;
                } else {
                    resp.sendRedirect("login.jsp");
                    return;
                }
            }
        } else {
            LOGGER.info("用户未登录，跳转登录界面");
            resp.sendRedirect("login.jsp");
            return;
        }
    }

    public void destroy() {

    }
}
