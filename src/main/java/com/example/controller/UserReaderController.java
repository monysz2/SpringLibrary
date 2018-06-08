package com.example.controller;

import com.example.model.*;
import com.example.service.ReaderService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class UserReaderController {
    @Autowired
    ReaderService readerService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/addReader", method = RequestMethod.GET)
    public ModelAndView getReader()
    {
        ModelAndView modelAndView = new ModelAndView();
        Reader reader = new Reader();
        String userEmail = new String();
        Set<User> users = userService.findAllUsersById();
        modelAndView.addObject("reader", reader);
        modelAndView.addObject("userEmail",userEmail);
        modelAndView.addObject("users",users);
        modelAndView.setViewName("user/addReader");
        return modelAndView;
    }

    @RequestMapping(value = "/user/saveReader", method = RequestMethod.POST)
    public ModelAndView addReader(@Valid Reader reader, BindingResult bindingResult)
    {

        ModelAndView modelAndView = new ModelAndView();
        readerService.addReader(reader);
        Set<User> users = userService.findAllUsersById();
        modelAndView.addObject("reader",new Reader());
        modelAndView.addObject("users",users);
        modelAndView.setViewName("user/addReader");
        return modelAndView;
    }
}
