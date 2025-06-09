package com.example.olibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "books")
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Comparable<Book>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = true, name = "description")
    private String description;
    @Column(nullable = false, name = "bookLink")
    private String bookFileName;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name="books_and_authors_matches",
            joinColumns=  @JoinColumn(name="book_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="author_id", referencedColumnName="id") )
    @JsonManagedReference
    private List<Author> authors;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name="books_and_genres_matches",
            joinColumns=  @JoinColumn(name="book_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="genre_id", referencedColumnName="id") )
    @JsonManagedReference
    private List<Genre> genres;

    @Column(nullable = false, name = "book_part")
    @Lob
    private String bookPart;

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Regal> regals;

    @OneToMany(mappedBy = "linkedBook", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Session> sessions;

    @Override
    public int compareTo(Book b) {
        return this.id.compareTo(b.id);
    }


}
