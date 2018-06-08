package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;
import java.util.SortedSet;

@Controller
public class UserManagementController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/admin/users", method= RequestMethod.GET )
    public ModelAndView users()
    {
        ModelAndView modelAndView = new ModelAndView();
        Set<User> users;
        User user = new User();
        users = userService.findAllUsersById();
        modelAndView.addObject("users", users);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("admin/users");
        return modelAndView;
    }

    @RequestMapping(value="/admin/deleteUser/{id}", method = RequestMethod.POST)
    public ModelAndView deleteUser(@PathVariable int id)
    {


        userService.deleteUser(id);
        ModelAndView modelAndView = new ModelAndView();
        Set<User> users;
        users = userService.findAllUsersById();
        modelAndView.addObject("successMessage", "User has been deleted successfully");
        modelAndView.addObject("users", users);
        modelAndView.setViewName("admin/users");
        return modelAndView;
    }
}
