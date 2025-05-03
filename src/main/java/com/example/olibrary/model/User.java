package com.example.olibrary.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Entity(name = "users")
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "username")
    private String username;
    @Column(nullable = false, name = "e_mail")
    private String eMail;
    @Column(nullable = false, name = "password")
    private String password;
    @Column(nullable = true, name = "first_name")
    private String firstName;
    @Column(nullable = true, name = "last_name")
    private String lastName;
    @Column(nullable = true, name = "middle_name")
    private String middleName;
    @Column(nullable = true, name = "account_created")
    private Date accountCreated;
    @Column(nullable = false, name = "birth_date")
    private Date birthDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private ArrayList<Regal> regals;
}