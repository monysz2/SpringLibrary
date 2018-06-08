package com.example.service;

import com.example.model.Author;
import com.example.model.Publisher;
import com.example.repository.AuthorRepository;
import com.example.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("publisherService")
public class PublisherServiceImpl implements PublisherService {


    @Autowired
    PublisherRepository publisherRepository;


    @Override
    public Set<Publisher> findAllPublishers()
    {
        return publisherRepository.findAllPublishers();
    }
}
