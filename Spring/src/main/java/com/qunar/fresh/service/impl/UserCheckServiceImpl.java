package com.qunar.fresh.service.impl;

import com.qunar.fresh.model.User;
import com.qunar.fresh.service.UserCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by kingsley.zhang on 17-3-16.
 */
@Service
public class UserCheckServiceImpl implements UserCheckService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCheckServiceImpl.class);

    @Override
    public boolean checkObjectNotNull(Object object) {
        if (object == null) {
            LOGGER.error("对象信息为空，{}", object);
            return false;
        }
        return true;
    }

    @Override
    public boolean checkUserValid(User user) {
        if (!checkObjectNotNull(user) || user.getIsValid() == null || user.getIsValid().equals(0)) {
            LOGGER.error("传入用户失效，请重新操作，user={}; 用户{}无效", user, user.getUsername());
            return false;
        }
        return true;
    }


    private boolean checkUpdateUserInfoNull(User user) {
        if (!checkObjectNotNull(user) || user.getGender() == null || user.getUsername() == null || user.getUsername().equals("") || user.getAge() == null) {
            LOGGER.error("传入 {} 用户信息的参数错误，参数为空，user={}; ", user.getUsername(), user);
            return false;
        }
        return true;
    }

    @Override
    public boolean checkUpdateUserValid(User user) {
        if (checkUpdateUserInfoNull(user)) {
            String username = user.getUsername();
            if (!username.matches("[a-zA-Z0-9_\u4e00-\u9fa5]*")) {
                LOGGER.error("用户{} 参数输入含有非法字符，输入错误，请重新输入，输入为字母，数字或者汉字", user.getUsername());
                return false;
            }
            int age = user.getAge();
            if (age < 0 || age > 200) {
                LOGGER.error("用户{} 年龄为{}，输入错误，请重新输入，(0,150)范围内", user.getUsername(), user.getAge());
                return false;
            }
            return true;
        }
        return false;
    }
}
