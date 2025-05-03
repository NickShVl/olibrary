package com.example.olibrary.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "genres")
@Table(name = "genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, name = "name")
    private String name;
    @Column(nullable = true, name = "description")
    private String description;
}
