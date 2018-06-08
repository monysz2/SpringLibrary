package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	 User findByEmail(String email);
	 Set<User> findAllByActiveIsBetween(int min, int max);
	 @Transactional
	 @Modifying
	 @Query("DELETE from User u where u.id=?")
	 void removeUserById(int id);
	 User findById(int id);


}
