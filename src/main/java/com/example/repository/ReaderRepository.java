package com.example.repository;

import com.example.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("readerRepository")
public interface ReaderRepository  extends JpaRepository<Reader,Integer>{
    Reader findById(int id);
    @Query("Select r FROM Reader r WHERE r.user.id=?")
    Reader findByUserId(int id);
    @Query("Select r FROM Reader r")
    Set<Reader> findAllReaders();

}
