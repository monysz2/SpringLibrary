package com.example.repository;

import com.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book,Integer> {
    Book findById(int id);
    Set<Book> findAllByAvailableBetween(String start, String end);
    @Query("SELECT b FROM Book b WHERE b.title=? AND b.available=?")
    @Modifying
    Set<Book> findAllByTitle(String title, String available);
    @Query("SELECT b FROM Book b WHERE b.isbn=? AND b.available=?")
    @Modifying
    Set<Book> findAllByIsbn(int isbn, String available);
    @Query("SELECT b FROM Book b WHERE b.id=?")
    @Modifying
    void removeBookById(int id);
    @Query("SELECT b FROM Book b WHERE b.available='N'")
    Set<Book> getAllAvailableBooks();
    @Query("SELECT b FROM Book b WHERE b.id=? AND b.available=?")
    Set<Book> getAllRentedUserBooks(int bookId, String available);

}
