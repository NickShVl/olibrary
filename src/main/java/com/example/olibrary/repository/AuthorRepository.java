package com.example.olibrary.repository;

import com.example.olibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findById(Long id);

    @Query("""
            SELECT a
            FROM authors a
            WHERE a.firstName LIKE %:name%
            OR a.lastName LIKE %:name%
            OR a.middleName LIKE %:name%
            OR a.alias LIKE %:name%
            """)
    ArrayList<Author> findAuthorsBySomeInformation(@Param("name") String name);

}