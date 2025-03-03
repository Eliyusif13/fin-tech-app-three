package com.sadiqov.tech_app_three.entity;

import com.sadiqov.tech_app_three.dto.request.AccountRequestDTO;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tech_user")
@Getter
@Setter
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
    List<Account> accountList;

    public void addAccountToUser(List<AccountRequestDTO> accountRequestDTOList) {
        accountList = new ArrayList<>();
        accountRequestDTOList.forEach(a -> accountList.add(Account.builder().
                balance(a.getBalance()).
                currency(a.getCurrency()).
                isActive(a.getIsActive()).
                accountNo(a.getAccountNo()).
                user(this).build()));
    }
}
