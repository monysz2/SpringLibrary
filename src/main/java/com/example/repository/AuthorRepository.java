package com.example.repository;

import com.example.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Set;

@Repository("authorRepository")
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Author findById(int id);
    @Query("SELECT a FROM Author a")
    Set<Author> findAllAuthors();
}
