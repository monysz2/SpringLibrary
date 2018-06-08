package com.example.service;

import com.example.model.Author;
import com.example.model.Publisher;

import java.util.Set;


public interface PublisherService {
    public Set<Publisher> findAllPublishers();
}
