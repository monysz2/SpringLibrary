package com.example.controller;

import com.example.model.Book;
import com.example.model.Reader;
import com.example.model.Rent;
import com.example.service.BookService;
import com.example.service.ReaderService;
import com.example.service.RentService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Controller
public class BookUserController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RentService rentService;

    @Autowired
    private ReaderService readerService;

    @RequestMapping(value = "/user/rentBook", method = RequestMethod.GET)
    public ModelAndView rentBook()
    {
        ModelAndView modelAndView = new ModelAndView();
        Set<Reader> readers = readerService.getAllReaders();
        Set<Book> books = bookService.findAllAvailableBooks();
        Rent rent = new Rent();
        rent.setRentDate(new Date());
        SimpleDateFormat ds = new SimpleDateFormat("dd.MM.yyyy");
        String date = ds.format(rent.getRentDate());
        modelAndView.addObject("date",date);
        modelAndView.addObject("rent",rent);
        modelAndView.addObject("readers",readers);
        modelAndView.addObject("books",books);
        modelAndView.setViewName("/user/rentBook");
        return modelAndView;
    }

    @RequestMapping(value = "/user/rentBook", method = RequestMethod.POST)
    public ModelAndView rentBookToUser(@Valid Rent rent, BindingResult bindingResult)
    {
        ModelAndView modelAndView= new ModelAndView();

        rent.setRentDate(new Date());
        Date returnDate = new Date();
        returnDate.setTime(rent.getRentDate().getTime() + 3600*1000*24*rent.getDays());
        rent.setReturnDate(returnDate);
        Book toChange = bookService.findBook(rent.getBook().getId());
        toChange.setAvailable("N");
        bookService.addBook(toChange);
        rentService.rentBook(rent);
        Set<Reader> readers = readerService.getAllReaders();
        Set<Book> books = bookService.findAllAvailableBooks();
        modelAndView.addObject("rent",new Rent());
        modelAndView.addObject("readers",readers);
        modelAndView.addObject("books",books);
        modelAndView.setViewName("/user/rentBook");
        return modelAndView;
    }

    @RequestMapping(name = "/user/readerService", method = RequestMethod.GET)
    public ModelAndView readerService()
    {
        ModelAndView modelAndView = new ModelAndView();
        Set<Reader> readers = readerService.getAllReaders();
        Reader readerSelect = new Reader();
        modelAndView.addObject("readers",readers);
        modelAndView.addObject("readerSelect",readerSelect);
        modelAndView.setViewName("user/readerService");
        return modelAndView;
    }

    @RequestMapping(name = "/user/readerService",method = RequestMethod.POST)
    public ModelAndView getReaderToServe(@Valid Reader readerSelect, BindingResult bindingResult)
    {
        ModelAndView modelAndView = new ModelAndView();
        Reader reader = readerSelect;
        Set<Rent> books = rentService.getAllUserBooks(reader.getId());
        modelAndView.addObject("reader",reader);
        modelAndView.addObject("books",books);
        modelAndView.setViewName("user/readerService");
        return modelAndView;

    }
}
