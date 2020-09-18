package com.jungo.jungocrawling.Account;


import javax.persistence.*;

@Entity
public class Account {

    @Id @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;




}
