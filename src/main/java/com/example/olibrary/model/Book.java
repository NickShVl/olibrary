package com.example.olibrary.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="books_and_authors_matches",
            joinColumns=  @JoinColumn(name="book", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="author", referencedColumnName="id") )
    private ArrayList<Author> authorsId;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="books_and_genres_matches",
            joinColumns=  @JoinColumn(name="book", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="genre", referencedColumnName="id") )
    private ArrayList<Genre> genresId;

    @Column(nullable = false)
    @Lob
    private String bookPart;
}
