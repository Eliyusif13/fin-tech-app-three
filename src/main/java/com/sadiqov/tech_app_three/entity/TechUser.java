package com.sadiqov.tech_app_three.entity;

import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "tech_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TechUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(name = "surname")
    String surName;
    String password;
    @Column(unique = true)
    String pin;
    String role;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    List<Account> accaountList;
}
