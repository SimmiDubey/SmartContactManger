package com.example.SmartContactManager.com.smart.dao;

import com.example.SmartContactManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
