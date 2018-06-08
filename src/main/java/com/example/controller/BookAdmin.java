package com.example.controller;

import com.example.model.Author;
import com.example.model.Book;
import com.example.model.Publisher;
import com.example.model.User;
import com.example.repository.AuthorRepository;
import com.example.service.AuthorService;
import com.example.service.BookService;
import com.example.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

@Controller
public class BookAdmin {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;

    private static String UPLOADED_FOLDER = "src//main//resources//static//images//";

    @RequestMapping(value = "/admin/books", method = RequestMethod.GET)
    public ModelAndView displayAllBooks()
    {
        ModelAndView modelAndView = new ModelAndView();
        Set<Book> books = bookService.findAllBooks();
        modelAndView.addObject("books",books);
        modelAndView.setViewName("admin/books");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/newBook", method = RequestMethod.GET)
    public ModelAndView getBook()
    {
        ModelAndView modelAndView = new ModelAndView();
        Book book = new Book();
        Set<Author> authors = authorService.findAllAuthors();
        Set<Publisher> publishers = publisherService.findAllPublishers();
        modelAndView.addObject("book",book);
        modelAndView.addObject("authors",authors);
        modelAndView.addObject("publishers",publishers);
        modelAndView.setViewName("admin/newBook");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/newBook", method = RequestMethod.POST)
    public ModelAndView addBook(@Valid Book book, @RequestParam("cover") MultipartFile file, BindingResult bindingResult) throws IOException {
        book.setAvailable("Y");

        ModelAndView modelAndView = new ModelAndView();
        Set<Author> authors = authorService.findAllAuthors();
        Set<Publisher> publishers = publisherService.findAllPublishers();
        byte[] bytes = file.getBytes();
        Path path = Paths.get( UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
        book.setCoverImage("/images/"+file.getOriginalFilename());
        bookService.addBook(book);
        modelAndView.addObject("book",new Book());
        modelAndView.addObject("authors",authors);
        modelAndView.addObject("publishers",publishers);
        modelAndView.setViewName("admin/newBook");
        return modelAndView;
    }
}
