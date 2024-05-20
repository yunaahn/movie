package com.example.movieapi.repository;


import com.example.movieapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existByUsername(String username);
}
