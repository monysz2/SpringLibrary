package com.example.repository;

import com.example.model.Book;
import com.example.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("rentRepository")
public interface RentRepository extends JpaRepository<Rent, Integer> {
    @Query("SELECT r FROM Rent r WHERE r.reader.id=? AND r.book.available=?")
    Set<Rent> findAllReadersBooks(int readerId, String available);
    @Query("SELECT r FROM Rent r WHERE r.reader.id=?")
    Set<Rent> getAllReaderHistory(int readerId);
    Rent findById(int id);
}
