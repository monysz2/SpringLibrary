package com.example.service;

import com.example.model.Book;
import com.example.model.Reader;

import java.util.Set;

public interface ReaderService {
    public Set<Book> getRentedBooks(String userName);
    public Reader getReader(int id);
    public void addReader(Reader reader);
    public Set<Reader> getAllReaders();
}
