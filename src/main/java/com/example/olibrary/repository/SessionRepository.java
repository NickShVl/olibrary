package com.example.olibrary.repository;

import com.example.olibrary.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
