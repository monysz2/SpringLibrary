package com.example.service;

import com.example.model.Book;
import com.example.model.Reader;
import com.example.model.User;
import com.example.repository.ReaderRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("readerService")
public class ReaderServiceImpl implements ReaderService{

    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Set<Book> getRentedBooks(String userName) {
        User user = userRepository.findByEmail(userName);
        return readerRepository.findById(user.getId()).getBooks();
    }
    @Override
    public Reader getReader(int id)
    {
        return readerRepository.findByUserId(id);
    }

    @Override
    public void addReader(Reader reader)
    {
        readerRepository.save(reader);
    }

    @Override
    public Set<Reader> getAllReaders() {
        return readerRepository.findAllReaders();
    }
}
