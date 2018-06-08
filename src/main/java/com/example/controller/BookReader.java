package com.example.controller;

import com.example.model.Book;
import com.example.model.Reader;
import com.example.model.User;
import com.example.repository.ReaderRepository;
import com.example.service.BookService;
import com.example.service.ReaderService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class BookReader {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


    @RequestMapping(value = "/reader/myBooks", method = RequestMethod.GET)
    public ModelAndView displayAllRentedBooks()
    {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Set<Book> books = readerService.getRentedBooks(name);
        User user = userService.findUserByEmail(name);
        Reader reader = readerService.getReader(user.getId());
        modelAndView.addObject("user",user);
        modelAndView.addObject("reader",reader);
        modelAndView.addObject("books",books);
        modelAndView.setViewName("reader/books");
        return modelAndView;
    }

    @RequestMapping(value = "/reader/library", method = RequestMethod.GET)
    public ModelAndView displayAllBooks()
    {
        ModelAndView modelAndView = new ModelAndView();
        Set<Book> books = bookService.findAllBooks();
        modelAndView.addObject("books",books);
        modelAndView.setViewName("reader/library");
        return modelAndView;
    }

}
