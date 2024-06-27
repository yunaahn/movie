package com.example.movieapi.repository;


import com.example.movieapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByUsername(String username);

    User findByUsername(String username);

    @Query("SELECT u.id FROM User u WHERE u.username = :username")
    Integer findIdByUsername(@Param("username") String username);
}
