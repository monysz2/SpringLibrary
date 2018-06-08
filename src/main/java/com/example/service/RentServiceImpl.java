package com.example.service;

import com.example.model.Book;
import com.example.model.Rent;
import com.example.repository.BookRepository;
import com.example.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("rentService")
public class RentServiceImpl implements RentService{

    @Autowired
    RentRepository rentRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public Set<Rent> getAllUserBooks(int readerId) {

        return rentRepository.findAllReadersBooks(readerId,"N");
    }

    @Override
    public void rentBook(Rent rent) {
        rentRepository.save(rent);
    }

    @Override
    public Rent getRentById(int id) {
        return rentRepository.findById(id);
    }

}
