package com.qunar.fresh.service;

import com.qunar.fresh.model.User;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class UserServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Resource
    UserService userService;

    @Test
    public void testInsertSingleUser() throws Exception {
        User user = getSingleUser();

        try {
            String result = userService.insertSingleUser(user);
            if (result == null) {
                LOGGER.error("插入数据错误");
            }
        } catch (Throwable e) {
            LOGGER.error("删除用户操作失败{},{}", user, e);
        }
    }

    @Test
    public void testDeleteSingleUser() throws Exception {
        User user = getSingleUser();
        try {
            String result = userService.deleteSingleUser(user);
            if (result == null) {
                LOGGER.error("逻辑删除用户错误");
            }
        } catch (Throwable e) {
            LOGGER.error("删除用户操作失败{},{}", user, e);
        }
    }

    @Test
    public void testUpdateSingleUser() throws Exception {
        User user = getSingleUser();
        user.setAge(122);
        try {
            userService.updateSingleUser(user);
            LOGGER.info(user.toString());
        } catch (Throwable e) {
            LOGGER.error("更新用户数据错误{},{}", user, e);
        }
    }

    @Test
    public void testQuerySingleUserById() throws Exception {
        User user = null;
        try {
            user = userService.querySingleUserById(2);
            LOGGER.info(user.toString());
        } catch (Throwable e) {
            LOGGER.error("更新用户数据错误{},{}", user, e);
        }
    }

    @Test
    public void testQueryAllUsers() throws Exception {
        List<User> listUsers = null;
        try {
            listUsers = userService.queryAllUsers();
            LOGGER.info(listUsers.toString());
        } catch (Throwable e) {
            LOGGER.error("查询所有用户操作错误{},{}", listUsers, e);
        }
    }

    private User getSingleUser() {
        User user = new User();
        user.setUsername("Tom");
        user.setIsValid(1);
        user.setGender(0);
        user.setAge(12);
        user.setRemark("student");
        user.setRegisterTime(new DateTime());
        return user;
    }
}