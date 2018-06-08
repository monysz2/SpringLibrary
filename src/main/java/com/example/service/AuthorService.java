package com.example.service;

import com.example.model.Author;
import com.example.model.Book;

import java.util.Set;


public interface AuthorService {
    public Set<Author> findAllAuthors();
}
