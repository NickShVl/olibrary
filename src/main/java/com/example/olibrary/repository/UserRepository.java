package com.example.olibrary.repository;

import com.example.olibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
}
