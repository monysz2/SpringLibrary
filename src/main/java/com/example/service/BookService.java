package com.example.service;

import com.example.model.Book;

import java.util.List;
import java.util.Set;


public interface BookService {
    public void addBook(Book book);
    public void removeBook(Book book);
    public Set<Book> findAllBooks();
    public Book findBook(int id);
    public Set<Book> findAllAvailableBooks();
}
