package com.example.service;

import com.example.model.Book;
import com.example.model.Reader;
import com.example.model.Rent;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface RentService {
    public Set<Rent> getAllUserBooks(int readerId);
    public void rentBook(Rent rent);
    public Rent getRentById(int id);
}
