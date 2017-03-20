package com.qunar.fresh.service.impl;

import com.google.common.base.Preconditions;
import com.qunar.fresh.dao.UserDao;
import com.qunar.fresh.model.User;
import com.qunar.fresh.service.UserCheckService;
import com.qunar.fresh.service.UserService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kingsley.zhang on 17-3-16.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    UserDao userDao;
    @Resource
    UserCheckService userCheckService;

    @Override
    public String insertSingleUser(User user) {
        Preconditions.checkNotNull(user);
        if (userCheckService.checkUpdateUserValid(user)) {
            user.setRegisterTime(new DateTime());
                int count = userDao.insertSingleUser(user);
                if (count == 1) {
                return "insertSingleUser success!!";
            } else {
                LOGGER.error("insertSingleUser Error,count={},传入参数{}", count, user);
            }
        }
        return null;
    }

    @Override
    public String deleteSingleUser(Integer id) {
        User user = new User();
        user.setId(id);
        user.setIsValid(1);
        return deleteSingleUser(user);
    }

    @Override
    public String deleteSingleUser(User user) {
        Preconditions.checkNotNull(user);
        if (userCheckService.checkUserValid(user)) {
            int count = userDao.deleteSingleUser(user);
            if (count == 1) {
                return "deleteSingleUser success!!";
            } else {
                LOGGER.error("deleteSingleUser Error,count={},传入参数{}", count, user);
            }
        }
        return null;
    }

    @Override
    public String updateSingleUser(User user) {
        Preconditions.checkNotNull(user);
        if (userCheckService.checkUpdateUserValid(user)) {
            int count = userDao.updateSingleUser(user);
            if (count == 1) {
                return "updateSingleUser success!!";
            } else {
                LOGGER.error("updateSingleUser Error,count={},传入参数{}", count, user);
            }
        }
        return null;
    }

    @Override
    public User querySingleUser(User userInfo) {
        Preconditions.checkNotNull(userInfo);
        if (userCheckService.checkUserValid(userInfo)) {
            User user = userDao.selectSingleUser(userInfo);
            if (user != null) {
                return user;
            } else {
                LOGGER.error("querySingleUser,user={} 传入参数{}", user, userInfo);
            }
        }
        return null;
    }

    @Override
    public User querySingleUserById(Integer id) {
        if (userCheckService.checkObjectNotNull(id)) {
            User user = new User();
            user.setId(id);
            user.setIsValid(1);
            return querySingleUser(user);
        }
        return null;
    }

    @Override
    public List<User> queryAllUsers() {
        List<User> userList = userDao.selectAllUsers();
        if (userList != null) {
            return userList;
        } else {
            LOGGER.error("querySingleUser,userList={}", userList);
            return null;
        }
    }
}
