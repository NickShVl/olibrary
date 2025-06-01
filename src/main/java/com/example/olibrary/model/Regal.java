package com.example.olibrary.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "regals")
@Table(name = "regals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Regal implements Comparable<Regal>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = true, name = "description")
    private String description;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name="books_and_regals_matches",
            joinColumns=  @JoinColumn(name="book_id"),
            inverseJoinColumns= @JoinColumn(name="regal_id") )
    @JsonManagedReference
    private List<Book> books;

    @Override
    public int compareTo(Regal r) {
        return this.id.compareTo(r.id);
    }
}
