package com.example.olibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "genres")
@Table(name = "genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre implements Comparable<Genre>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, name = "name")
    private String name;
    @Column(nullable = true, name = "description")
    private String description;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Book> books;

    @Override
    public int compareTo(Genre g) {
        return this.id.compareTo(g.id);
    }
}
