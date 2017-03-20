package com.qunar.fresh.web;

import com.qunar.fresh.model.User;
import com.qunar.fresh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kingsley.zhang on 17-3-16.
 */
@Controller
public class UserViewControl {

    @Autowired
    UserService userService;

    /**
     * 查看所有用户,跳转到list.jsp
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String searchUsers(Model model) {
        model.addAttribute("userList", userService.queryAllUsers());
        return "list";
    }

    /**
     * 查看单个用户的详细信息，跳转到detail.jsp
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String querySingleUser(Integer id, Model model) {
        User user = userService.querySingleUserById(id);
        model.addAttribute("singleUser", user);
        return "detail";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addSingleUser() {
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addSingleUser(User user) {
        System.out.println("into add");
        if (userService.insertSingleUser(user) == null) {
            return "error";
        }
        return "success";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteSingleUser(Integer id) {
        if (userService.deleteSingleUser(id) == null) {
            return "error";
        }
        return "success";
    }

    @RequestMapping(value = "/modifyUser", method = RequestMethod.GET)
    public String modifyUser(Integer id, Model model) {
        model.addAttribute("singleUser", userService.querySingleUserById(id));
        return "modifyUser";
    }

    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    public String modifyUser(User user) {
        if (userService.updateSingleUser(user) == null) {
            return "error";
        }
        return "success";
    }
}
