package com.example.olibrary.repository;

import com.example.olibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findById(Long id);

    @Query("""
            SELECT b
            FROM books b
            WHERE b.name LIKE %:name%
            """)
    ArrayList<Book> findBookByName(@Param("name") String name);

    @Query("""
            SELECT b
            FROM books b
            JOIN genres g
            WHERE g.name LIKE %:name%
            """)
    List<Book> findBookByGenreName(
            @Param("name") String name
    );

    @Query("""
            SELECT b
            FROM books b
            JOIN authors a
            WHERE a.firstName LIKE %:name%
            OR a.lastName LIKE %:name%
            OR a.middleName LIKE %:name%
            OR a.alias LIKE %:name%
            """)
    List<Book> findBookByAuthorInfo(
            @Param("name") String name
    );

}