package com.qunar.fresh2017.service.impl;

import com.google.common.collect.Maps;
import com.qunar.fresh2017.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by kingsley.zhang on 2017/3/23.
 * 服务层，与用户信息相关的服务
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    private static Map<String, String> userMap; // 记录用户名和密码
    private static final String ACCOUNT_PATH = "classpath:account.properties"; // 用户名密码的配置文件位置
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    /*
     * 初始化块，读取用户名和密码到userMap
     */
    static {
        FileInputStream fileInputStream = null;
        try {
            ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext();
            Resource resource = cx.getResource(ACCOUNT_PATH);
            Properties properties = new Properties();
            fileInputStream = new FileInputStream(resource.getFile());
            properties.load(fileInputStream);
            userMap = Maps.fromProperties(properties);
        } catch (IOException e) {
            LOGGER.error("读取account.properties文件异常", e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    LOGGER.error("关闭文件流异常", e);
                }
            }
        }
    }

    /*
     * 判断是否登录成功
     */
    @Override
    public boolean login(String userName, String password) {

        if (userMap.containsKey(userName)) {
            if (userMap.get(userName).equals(password)) {
                return true;
            }
        }
        return false;
    }
}
