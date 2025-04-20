package com.example.olibrary.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String eMail;
    @Column(nullable = false)
    private String password;
    @Column(nullable = true)
    private String firstName;
    @Column(nullable = true)
    private String lastName;
    @Column(nullable = true)
    private String middleName;
    @Column(nullable = true)
    private Date accountCreated;
    @Column(nullable = false)
    private Date birthDate;
    @OneToMany
    @JoinTable(name = "regals")
    private ArrayList<Regal> regals;
}