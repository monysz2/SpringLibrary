package com.example.repository;

import com.example.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("publisherRepository")
public interface PublisherRepository extends JpaRepository<Publisher,Integer>{
    Publisher findById(int id);
    @Query("SELECT p FROM Publisher p")
    Set<Publisher> findAllPublishers();
}
