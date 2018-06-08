package com.example.service;

import com.example.model.Author;
import com.example.model.Book;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import com.example.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {


    @Autowired
    AuthorRepository authorRepository;


    @Override
    public Set<Author> findAllAuthors()
    {
        return authorRepository.findAllAuthors();
    }
}
