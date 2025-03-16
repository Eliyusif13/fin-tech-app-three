package com.sadiqov.tech_app_three.entity;

import com.sadiqov.tech_app_three.dto.request.AccountRequestDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "user_name")
    String name;
    @Column(name = "user_suranme")
    String surName;
    @Column(name = "password")
    String password;
    @Column(name="pin",unique = true)
    String pin;
    @Column(name = "role")
    String role;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    @Column(name = "account_list")
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
