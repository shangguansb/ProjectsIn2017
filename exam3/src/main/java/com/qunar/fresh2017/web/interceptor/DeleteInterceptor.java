package com.qunar.fresh2017.web.interceptor;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kingsley.zhang on 2017/3/23.
 * 拦截器，防止游客通过输入网址的方式删除历史文件差异记录
 */
public class DeleteInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteInterceptor.class);

    /*
     * 如果未登录的用户访问deleteFileDiff.do，则进行拦截，并跳转到登录页面
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String userName = (String) request.getSession().getAttribute("userName");
        if (Strings.isNullOrEmpty(userName)) {
            response.sendRedirect("/index.do");
            return false;
        }
        return true;
    }
}
