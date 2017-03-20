package com.qunar.fresh.dao.impl;

import com.qunar.fresh.model.User;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class UserDaoImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImplTest.class);

    @Resource
    private UserDaoImpl userDaoImpl;

    @Test
    public void test() {
        Date dateTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = simpleDateFormat.format(dateTime);
        System.out.println(str);
    }

    @Test
    public void testInsertSingleUser() throws Exception {
        User user = getSingleUser();
        try {
            userDaoImpl.insertSingleUser(user);
        } catch (Throwable e) {
            LOGGER.error("插入用户数据错误{},{}", user, e);
        }
    }

    @Test
    public void testDeleteSingleUser() throws Exception {
        User user = null;
        try {
            user = new User();
            user.setId(1);
            userDaoImpl.deleteSingleUser(user);
        } catch (Throwable e) {
            LOGGER.error("插入用户数据错误{},{}", user, e);
        }
    }

    @Test
    public void testUpdateSingleUser() throws Exception {
        User user = getSingleUser();
        user.setId(2);
        try {
            userDaoImpl.updateSingleUser(user);
            user = userDaoImpl.selectSingleUser(user);
            LOGGER.info(user.toString());
        } catch (Throwable e) {
            LOGGER.error("更新用户数据错误{},{}", user, e);
        }
    }

    @Test
    public void testSelectAllUsers() throws Exception {
        List<User> listUsers = null;
        try {
            listUsers = userDaoImpl.selectAllUsers();
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