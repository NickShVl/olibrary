package com.example.olibrary.repository;

import com.example.olibrary.model.Book;
import com.example.olibrary.model.Regal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegalRepository extends JpaRepository<Regal, Long> {
    Optional<Regal> findById(Long id);

    @Query("""
            SELECT r
            FROM regals r
            WHERE r.name LIKE %:name%
            """)
    List<Regal> findRegalByName(@Param("name") String name);

    @Query("""
            SELECT b
            FROM regals r
            JOIN books b
            WHERE b.name LIKE %:name%
            AND r.id = :id
            """)
    List<Book> findBookOnRegalByBookName(
            @Param("id") Long id,
            @Param("name") String name
    );

    @Query("""
            SELECT b
            FROM regals r
            JOIN books b
            JOIN genres g
            WHERE g.name LIKE %:name%
            AND r.id = :id
            """)
    List<Book> findBookOnRegalByGenreName(
            @Param("id") Long id,
            @Param("name") String name
    );
    @Query("""
            SELECT b
            FROM regals r
            JOIN books b
            JOIN authors a
            WHERE (a.firstName LIKE %:name%
            OR a.lastName LIKE %:name%
            OR a.middleName LIKE %:name%
            OR a.alias LIKE %:name%)
            AND r.id = :id
            """)
    List<Book> findBookOnRegalByAuthorInfo(
            @Param("id") Long id,
            @Param("name") String name
    );
}
