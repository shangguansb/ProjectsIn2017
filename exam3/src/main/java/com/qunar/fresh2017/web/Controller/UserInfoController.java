package com.qunar.fresh2017.web.Controller;

import com.google.common.base.Strings;
import com.qunar.fresh2017.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kingsley.zhang on 2017/3/23.
 * 控制层，一些用户登录，退出登录方面的控制器
 */
@Controller
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

    /*
     * 登录功能，如果登录成功，在Session中写入userName。 无论是否登录成功，都会跳转到上传文件页面，但看到的页面和权限不同
     */
    @RequestMapping(value = "/login.do")
    @ResponseBody
    public ModelAndView login(String userName, String password, HttpServletRequest request) {
        if (!Strings.isNullOrEmpty(userName) && !Strings.isNullOrEmpty(password)) {
            boolean login = userInfoService.login(userName, password);
            if (login) {
                request.getSession().setAttribute("userName", userName);
            }
        }
        return new ModelAndView("redirect:/uploadFile.do?startId=0");
    }

    /*
     * 起始页面：显示登录页面
     */
    @RequestMapping(value = "/index.do")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /*
     * 用户退出登录 在Session中删除userName 跳转到上传文件页面
     */
    @RequestMapping(value = "/logout.do")
    @ResponseBody
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().removeAttribute("userName");
        return new ModelAndView("redirect:/uploadFile.do?startId=0");
    }
}
