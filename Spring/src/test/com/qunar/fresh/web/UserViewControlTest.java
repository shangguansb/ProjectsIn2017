package com.qunar.fresh.web;

import com.qunar.fresh.model.User;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(locations = {
        "classpath:spring/applicationContext.xml",
        "classpath:spring/springMVC.xml"})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UserViewControlTest {

    protected MockMvc mockMvc;
    @Resource
    UserViewControl userViewControl;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testSearchUsers() throws Exception {
        mockMvc.perform((get("/list"))).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testQuerySingleUser() throws Exception {
        mockMvc.perform((get("/detail?id=2"))).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testAddSingleUser() throws Exception {
        User user = getSingleUser();
        MockHttpServletRequestBuilder builder = get("/addUser");
        ResultActions ac = mockMvc.perform(builder);
        //输出测试信息
        ac.andDo(print());
        ac.andExpect(status().isOk());
        ac.andExpect(MockMvcResultMatchers.view().name("addUser"));
//        ac.andExpect(MockMvcResultMatchers.model().attributeExists("list"));

    }

    @Test
    public void testAddSingleUser1() throws Exception {
        User user = getSingleUser();
        MockHttpServletRequestBuilder builder = post("/addUser");
        builder.param("username", user.getUsername());
        builder.param("age", user.getAge().toString());
        builder.param("gender", user.getGender().toString());
        builder.param("isValid", user.getIsValid().toString());
        builder.param("remark", user.getRemark().toString());
        ResultActions ac = mockMvc.perform(builder);
        //输出测试信息
        ac.andDo(print());
        ac.andExpect(status().isOk());
        ac.andExpect(MockMvcResultMatchers.view().name("success"));

    }

    @Test
    public void testDeleteSingleUser() throws Exception {
        mockMvc.perform(get("/deleteUser").param("id", "1")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testModifyUser() throws Exception {
        User user = getSingleUser();
        MockHttpServletRequestBuilder builder = post("/modifyUser");
        builder.param("id", "2");
        builder.param("username", user.getUsername());
        builder.param("age", user.getAge().toString());
        builder.param("gender", user.getGender().toString());
        builder.param("isValid", user.getIsValid().toString());
        builder.param("remark", user.getRemark().toString());
        ResultActions ac = mockMvc.perform(builder);
        //输出测试信息
        ac.andDo(print());
        ac.andExpect(status().isOk());
        ac.andExpect(MockMvcResultMatchers.view().name("success"));
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