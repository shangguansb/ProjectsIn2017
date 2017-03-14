package com.quanr.Servlet;

import com.quanr.Utils.QunarUtils;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by kingsley.zhang on 2017/3/9.
 */
public class LogoutServLet extends HttpServlet {
    public static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RegistServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QunarUtils.removeCookies(req, resp);
        LOGGER.info("登出成功");
        resp.sendRedirect("/login.jsp");
    }
}
