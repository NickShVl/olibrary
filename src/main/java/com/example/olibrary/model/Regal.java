package com.example.olibrary.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity(name = "regals")
@Table(name = "regals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Regal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = true, name = "description")
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="books_and_regals_matches",
            joinColumns=  @JoinColumn(name="book_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="regal_id", referencedColumnName="id") )
    private ArrayList<Book> books;
}
