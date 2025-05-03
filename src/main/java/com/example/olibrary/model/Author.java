package com.example.olibrary.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "authors")
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author{
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
}


