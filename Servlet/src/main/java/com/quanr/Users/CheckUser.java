package com.quanr.Users;

import com.google.common.base.Strings;
import com.quanr.dao.UserDao;
import com.quanr.dao.imlp.UserDaoImpl;

/**
 * Created by kingsley.zhang on 2017/3/8.
 */

public class CheckUser {
    // public static Map<String, User> userMap = new ConcurrentHashMap<String, User>();    // 线程安全
    public final static int SUCCESS = 1;
    public final static int FALSE = 2;

    public static int regist(User user) {
        UserDao userDao = new UserDaoImpl();
        User userTemp = userDao.queryUserByName(user.getUsername());
        if (user != null
                && !Strings.isNullOrEmpty(user.getUsername())
                && !Strings.isNullOrEmpty(user.getPassword())
                && !Strings.isNullOrEmpty(user.getNickname())) {
            userDao.insertUser(user);
            return SUCCESS;
        }

        return FALSE;// 用户名 已存在或不合法
    }

    public static int login(User user) {
        String username = user.getUsername();
        UserDao userDao = new UserDaoImpl();
        User userTemp = userDao.queryUserByName(username);
        if (!Strings.isNullOrEmpty(username)
                && userTemp != null
                && user.getPassword()
                .equals(userTemp.getPassword())) {
            return SUCCESS;             // 查找成功
        }
        return FALSE;
    }

}