package com.qunar.fresh2017.service;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml", "classpath:/mvc-dispatcher-servlet.xml" })
public class UserInfoServiceTest {

    @Resource
    private UserInfoService userInfoService;

    @Test
    public void testLogin() throws Exception {
        boolean flag = userInfoService.login("zha.zheng", "123456");
        Assert.assertEquals(flag, true);
    }
}