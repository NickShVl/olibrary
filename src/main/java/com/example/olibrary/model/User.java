package com.example.olibrary.model;

import com.example.olibrary.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Comparable<User>, UserDetails {
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

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private List<Regal> regals;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner")
    @JsonManagedReference
    private List<Session> sessions;

    @Override
    public int compareTo(User u) {
        return this.id.compareTo(u.id);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }



}