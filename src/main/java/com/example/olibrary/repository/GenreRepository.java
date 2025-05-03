package com.example.olibrary.repository;

import com.example.olibrary.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query("""
            SELECT g
            FROM books g
            WHERE g.name LIKE %:name%
            """)
    ArrayList<Genre> findGenreByName(@Param("name") String name);

}
