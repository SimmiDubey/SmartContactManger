package com.example.SmartContactManager.com.smart.dao;

import com.example.SmartContactManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByUserName(@Param("email") String email);

 }

