package com.sadiqov.tech_app_three.entity;

import com.sadiqov.tech_app_three.util.Currency;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Table(name = "user_accounts")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "balance")
    BigDecimal balance;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    Currency currency;

    @Column(name = "status")
    Boolean isActive;

    @Column(name = "account_no")
    Integer accountNo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    TechUser user;

}
