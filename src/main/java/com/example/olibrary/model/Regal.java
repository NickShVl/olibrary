package com.example.olibrary.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity
@Table(name = "regals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Regal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="books_and_regals_matches",
            joinColumns=  @JoinColumn(name="regal", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="book", referencedColumnName="id") )
    private ArrayList<Book> booksId;
}
