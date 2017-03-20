package com.qunar.fresh.extention.interceptors;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kingsley.zhang on 17-3-16.
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("拦截请求的Uri:{},请求方式：{},开始时间：{}", request.getRequestURI(), request.getMethod(), new DateTime());
        LOGGER.info("request ip:{}", request.getRemoteAddr());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("拦截请求的Uri:{}, 请求方式：{},结束时间: {}", request.getRequestURI(), request.getMethod(), new DateTime());
    }

}
