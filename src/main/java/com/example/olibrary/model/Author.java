package com.example.olibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity(name = "authors")
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author implements Comparable<Author>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true, name = "alias")
    private String alias;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(nullable = true, name = "birth_date")
    private Date birthDate;
    @Column(nullable = true, name = "death_date")
    private Date deathDate;
    @Column(nullable = false, name = "century")
    private int century;
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Book> books;

    @Override
    public int compareTo(Author a){
        return this.id.compareTo(a.id);
    }
}


