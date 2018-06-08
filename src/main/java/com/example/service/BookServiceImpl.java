package com.example.service;

import com.example.model.Author;
import com.example.model.Book;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import com.example.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;


    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void removeBook(Book book) {
      //  bookRepository.removeBookById(book.getId());
    }

    @Override
    public Set<Book> findAllBooks() {
        Set<Book> books = bookRepository.findAllByAvailableBetween("N","Y");
        return books;
    }

    @Override
    public Book findBook(int id)
    {
        return bookRepository.findById(id);
    }

    @Override
    public Set<Book> findAllAvailableBooks() {
        return bookRepository.getAllAvailableBooks();
    }


}
